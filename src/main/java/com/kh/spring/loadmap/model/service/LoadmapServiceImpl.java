package com.kh.spring.loadmap.model.service;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHCommit.File;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHTree;
import org.kohsuke.github.GHTreeEntry;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.common.util.json.JsonMaker;
import com.kh.spring.loadmap.model.dto.GitCommit;
import com.kh.spring.loadmap.model.dto.Loadmap;
import com.kh.spring.loadmap.model.repository.LoadmapRepository;

@Service
public class LoadmapServiceImpl implements LoadmapService {

	@Autowired
	LoadmapRepository loadmapRepository;

	@Override
	public String insertGit(Loadmap loadmap) {

		// 로드맵 주소 세팅
		List<Map<String, Object>> paths = new ArrayList<>();
		if (!loadmap.getGitRepo().contains("https://github.com/")) {
			return "fail";
		}
		loadmap.setGitRepo(loadmap.getGitRepo().replace("https://github.com/", ""));

		Loadmap beforeLoadmap = loadmapRepository.selectLoadmapByWsIdx(loadmap.getWsIdx());
		GitCommit gitCommit = null;

		if (beforeLoadmap != null) {

			loadmapRepository.deleteLoadmapByWsIdx(beforeLoadmap.getWsIdx());
		}

		try {

			GHRepository repo = getGitRepo(loadmap);
			makeGitTree(repo, loadmap);

			gitCommit = getCommitFileShaList(repo);

		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}

		loadmapRepository.insertGit(loadmap);
		gitCommit.setLmIdx(loadmap.getLmIdx());

		loadmapRepository.insertGitCommit(gitCommit);

		System.out.println(loadmap);

		return "complete";
	}

	private GHRepository getGitRepo(Loadmap loadmap) throws IOException {

		GitHub github = new GitHubBuilder()
				.withOAuthToken("ghp_xY7zcQwkJZ=JGkXGcBOhH3g=MW6Akxbx0F7pkW".replace("=", "")).build();
		GHRepository repo = github.getRepository(loadmap.getGitRepo());

		return repo;

	}

	private GitCommit getCommitFileShaList(GHRepository repo) throws IOException {

		GitCommit gitCommit = new GitCommit();
		List<String> fileSha = new ArrayList<>();
		for (GHCommit g : repo.listCommits()) {

			gitCommit.setLogin(g.getCommitter().getLogin());
			gitCommit.setMessage(g.getCommitShortInfo().getMessage());
			gitCommit.setCommitDate(new Date(g.getCommitDate().getTime()));

			for (File f : repo.getCommit(g.getSHA1()).getFiles()) {
				System.out.println("추가 파일: " + f.getSha());
				String[] fileUrl = f.getFileName().split("/");
				fileSha.add(fileUrl[fileUrl.length - 1]);
			}

			break; // 1번만 돌고 멈춤
		}

		gitCommit.setFiles(fileSha);
		System.out.println(gitCommit);

		return gitCommit;

	}

	private List<Map<String, Object>> makeGitTree(GHRepository repo, Loadmap loadmap) throws Exception {

		// 깃 브랜치 등록
		GHTree ghTree = repo.getTree(loadmap.getBranch());
		List<GHTreeEntry> treeList = ghTree.getTree();

		Queue<Map<String, Object>> q = new LinkedList<>();
		List<Map<String, Object>> paths = new ArrayList<>();
		for (int i = 0; i < treeList.size(); i++) {

			if (treeList.get(i).getType().equals("tree")) {
				String sha = treeList.get(i).getSha();
				String path = treeList.get(i).getPath();
				System.out.println(sha);
				System.out.println(path);
				Map<String, Object> m = new HashMap<>();
				m.put("sha", sha.substring(0, 8));
				m.put("path", path);
				m.put("type", "tree");
				paths.add(m);

				q.offer(m);

				while (q.size() != 0) {

					Map<String, Object> thisM = q.poll();
					List<GHTreeEntry> lt = repo.getTree((String) thisM.get("sha")).getTree();
					for (int j = 0; j < lt.size(); j++) {

						Map<String, Object> m2 = new HashMap<>();
						String p = (String) thisM.get("path");

						boolean ignoring = ignoring(p, loadmap.getIgnoreList());
						if (ignoring)
							break;

						if (lt.get(j).getType().equals("tree")) {

							m2.put("prev", (String) thisM.get("sha"));
							m2.put("sha", lt.get(j).getSha().substring(0, 8));
							m2.put("path", lt.get(j).getPath());
							m2.put("type", "tree");
							q.offer(m2);
							System.out.println(m2);

						} else if (lt.get(j).getType().equals("blob")) {

							m2.put("prev", (String) thisM.get("sha"));
							m2.put("path", lt.get(j).getPath());
							m2.put("sha", lt.get(j).getSha().substring(0, 8));
							m2.put("type", "blob");
							System.out.println(m2);
							System.out.println(lt.get(j).getSha());
						}

						paths.add(m2);

					}

				}

			}

		}

		loadmap.setGitTree(JsonMaker.json(paths));
		return paths;

	}

	private boolean ignoring(String path, List<String> ignore) {

		for (String dir : ignore) {

			if (path.equals(dir)) {
				return true;
			}

		}

		return false;
	}

	@Override
	public Loadmap selectLoadmap(String wsIdx) {

		Loadmap loadmap = loadmapRepository.selectLoadmapByWsIdx(wsIdx);

		return loadmap;
	}

	@Override
	public List<GitCommit> selectGitCommitListByLmIdx(String lmIdx) {

		return loadmapRepository.selectGitCommitListByLmIdx(lmIdx);
	}

	@Override
	public GitCommit selectNewCommitList(Loadmap loadmap) {

		GitCommit gitCommit = null;

		try {

			GHRepository repo = getGitRepo(loadmap);
			gitCommit = getCommitFileShaList(repo);
			System.out.println("다시 받아온 깃커밋:  " + gitCommit);

		} catch (IOException e) {
			e.printStackTrace();
		}
		List<GitCommit> lastGitList = loadmapRepository.selectGitCommitListByLmIdx(loadmap.getLmIdx());
		System.out.println(lastGitList);
		if (lastGitList != null) {

			GitCommit lastGit = lastGitList.get(0);
			if (lastGit.getCommitDate().getTime() == gitCommit.getCommitDate().getTime()) {
				System.out.println("최신과 같음 걸림");
				return null;
			} else {

				System.out.println("여기걸림");
				gitCommit.setLmIdx(loadmap.getLmIdx());
				loadmapRepository.insertGitCommit(gitCommit);

			}

		}

		return gitCommit;
	}

}
