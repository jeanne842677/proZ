package com.kh.spring.loadmap.controller;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHTree;
import org.kohsuke.github.GHTreeEntry;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.kh.spring.loadmap.model.service.LoadmapService;
import com.kh.spring.loadmap.model.dto.Loadmap;

@RequestMapping("loadmap")
@Controller
public class LoadmapController {

	@Autowired
	private LoadmapService roadMapService;

	@Autowired
	RestTemplate http;
	
	@GetMapping("loadmap")
	public void roadmap(Model model) {
		
		
	}
	
	
	
	@PostMapping("/git/upload")
	@ResponseBody
	public String gitUpload(@RequestBody Loadmap loadmap) {
		System.out.println(loadmap);
		String projectIdx= "999998";
		String res = roadMapService.insertGit(projectIdx ,loadmap);
		
		System.out.println(res);
		
		return "complete";
	}
	
	

	@GetMapping("loadmap2")
	public void roadmap2(Model model) {
		
	

		/*
		 * 
		 * 
		 * String url = "https://api.github.com/users/sazzeo/repos"; HttpHeaders headers
		 * = new HttpHeaders(); headers.set("Accept"
		 * ,"application/vnd.github.nightshade-preview+json");
		 * headers.set("Authorization"
		 * ,"Token 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");
		 * 
		 * 
		 * HttpEntity entity = new HttpEntity(headers);
		 * 
		 * Map<String, String> params = new HashMap<>(); params.put("org", "proZ");
		 * 
		 * 
		 * ResponseEntity<String> res= http.exchange(url, HttpMethod.GET , entity ,
		 * String.class , params);
		 * 
		 * System.out.println("내꺼" + res.getBody()); String json = res.getBody();
		 * 
		 * ObjectMapper mapper = new ObjectMapper(); List<Map<String, Object>> list =
		 * null; try { list = mapper.readValue(json, new
		 * TypeReference<List<Map<String,Object>>>(){}); } catch
		 * (JsonProcessingException e) { System.out.println("제이슨 에러"); }
		 * 
		 * System.out.println(list);
		 * 
		 * for(Map<String, Object> m : list ) {
		 * 
		 * if(m.get("name").equals("proZ")) {
		 * 
		 * 
		 * break; }
		 * 
		 * }
		 * 
		 * 
		 * 
		 * try { GitHub github = new
		 * GitHubBuilder().withOAuthToken("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").
		 * build(); GHRepository repo = github.getRepository("sazzeo/proZ");
		 * 
		 * System.out.println("여기: " + repo.getTree("main")); GHTree ghTree =
		 * repo.getTree("main"); List<GHTreeEntry> treeList = ghTree.getTree();
		 * System.out.println(treeList);
		 * 
		 * for (int i = 0; i < treeList.size(); i++) {
		 * 
		 * System.out.println(treeList.get(i).getPath());
		 * 
		 * }
		 * 
		 * } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace();
		 * 
		 * }
		 */
		
	}

	
	@PostMapping("draw")
	@ResponseBody
	public List<Map<String, Object>> draw() {
		List<Map<String, Object>> paths = new ArrayList<>();

		try {
			GitHub github = new GitHubBuilder().withOAuthToken("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").build();
			GHRepository repo = github.getRepository("sazzeo/proZ/src");
			System.out.println("여기: " + repo.getTree("main"));
			GHTree ghTree = repo.getTree("main");
			List<GHTreeEntry> treeList = ghTree.getTree();
			System.out.println(treeList);

			Queue<Map<String, Object>> q = new LinkedList<>();

			for (int i = 0; i < treeList.size(); i++) {

				if (treeList.get(i).getType().equals("tree")) {
					String sha = treeList.get(i).getSha();
					String path = treeList.get(i).getPath();
					System.out.println(sha);
					System.out.println(path);

					Map<String, Object> m = new HashMap<>();
					m.put("sha", sha);
					m.put("path", path);

					q.offer(m);

					while (q.size() != 0) {

						Map<String, Object> thisM = q.poll();
						List<GHTreeEntry> lt = repo.getTree((String) thisM.get("sha")).getTree();
						for (int j = 0; j < lt.size(); j++) {

							Map<String, Object> m2 = new HashMap<>();
							if (lt.get(j).getType().equals("tree")) {
								
								m2.put("prev", (String) thisM.get("path"));
								m2.put("sha", lt.get(j).getSha());
								m2.put("path", lt.get(j).getPath());
								q.offer(m2);
								System.out.println(m2);
							} else if (lt.get(j).getType().equals("blob")) {

								m2.put("prev", (String) thisM.get("path"));
								m2.put("path", lt.get(j).getPath());
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

		System.out.println(paths);

		return paths;
	}
	
	

}
