package com.kh.spring.loadmap.model.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

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
		
		makeGitTree(loadmap);
		
		loadmapRepository.insertGit(loadmap);
		
		System.out.println(loadmap);
		
		
		return "complete";
	}
	
	

	
	
	private List<Map<String, Object>> makeGitTree(Loadmap loadmap) {
		
	
		List<Map<String, Object>> paths = new ArrayList<>();

		try {
			loadmap.setGitRepo(loadmap.getGitRepo().replace("https://github.com/", ""));
			
			//깃 레포지토리 등록
			GitHub github = new GitHubBuilder().withOAuthToken("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").build();
			GHRepository repo = github.getRepository(loadmap.getGitRepo());
			
			//깃 브랜치 등록
			GHTree ghTree = repo.getTree(loadmap.getBranch());
			List<GHTreeEntry> treeList = ghTree.getTree();
			System.out.println(treeList);

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
								q.offer(m2);
								System.out.println(m2);
							} else if (lt.get(j).getType().equals("blob")) {
								
								
								m2.put("prev", (String) thisM.get("sha"));
								m2.put("path", lt.get(j).getPath());
								m2.put("sha", lt.get(j).getSha().substring(0, 8));
								System.out.println(m2);
							}

							paths.add(m2);

						}

					}

				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

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
