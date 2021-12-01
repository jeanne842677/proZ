package com.kh.spring.loadmap.model.service;

import java.io.IOException;
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
import com.kh.spring.loadmap.model.dto.Loadmap;
import com.kh.spring.loadmap.model.repository.LoadmapRepository;

@Service
public class LoadmapServiceImpl implements LoadmapService {
	
	@Autowired
	LoadmapRepository loadmapRepository;
	

	@Override
	public String insertGit(Loadmap loadmap) {
		
		
		Loadmap beforeLoadmap = loadmapRepository.selectLoadmapByWsIdx(loadmap.getWsIdx());
		
		if(beforeLoadmap!=null) {
		
			loadmapRepository.deleteLoadmapByWsIdx(beforeLoadmap.getWsIdx());
		}

	
		try {
			
			makeGitTree(loadmap);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		
		
		loadmapRepository.insertGit(loadmap);
		
		System.out.println(loadmap);
		
		
		return "complete";
	}
	
	
	
	private Map<String, Object> getCommitFileShaList(GHRepository repo) throws IOException {
		
		Map<String, Object> commits = new HashMap<>();
		List<String> fileSha = new ArrayList<>();
		for(GHCommit g :repo.listCommits()) {
			
			
			commits.put("login", g.getCommitter().getLogin());
			commits.put("message",  g.getCommitShortInfo().getMessage());
			commits.put("date", g.getCommitDate());
			for(File f : repo.getCommit(g.getSHA1()).getFiles()) {
				System.out.println("추가 파일: " + f.getSha());
				fileSha.add(f.getSha().substring(0, 8));
			}
			
			break; //1번만 돌고 멈춤
		}
		
		commits.put("file", fileSha);
		
		return commits;
		
	}
	
	
	private List<Map<String, Object>> makeGitTree(Loadmap loadmap) throws Exception {
		
	
		List<Map<String, Object>> paths = new ArrayList<>();
			if(!loadmap.getGitRepo().contains("https://github.com/")) {
			throw new Exception("주소 에러");
			}
		

			loadmap.setGitRepo(loadmap.getGitRepo().replace("https://github.com/", ""));
			
			
			//깃 레포지토리 등록
			GitHub github = new GitHubBuilder().withOAuthToken("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").build();
			
			GHRepository repo = github.getRepository(loadmap.getGitRepo());
			
			System.out.println(getCommitFileShaList(repo)); 
			
			
			
			//깃 브랜치 등록
			GHTree ghTree = repo.getTree(loadmap.getBranch());
			List<GHTreeEntry> treeList = ghTree.getTree();
		

			Queue<Map<String, Object>> q = new LinkedList<>();

			for (int i = 0; i < treeList.size(); i++) {

				if (treeList.get(i).getType().equals("tree") ) {
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
							
							
							boolean ignoring = ignoring(p , loadmap.getIgnoreList());
							if(ignoring) break;
							
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
	
	private boolean ignoring( String path ,List<String> ignore) {
		
		for(String dir :ignore) {
			
			if(path.equals(dir)) {
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




}
