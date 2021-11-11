package com.kh.spring.project.model.service;

import com.kh.spring.project.model.dto.Project;

public interface ProjectService {

	void updateProjectInviteCode(String projectIdx);

	Project selectProjectByIdx(String projectIdx);

}
