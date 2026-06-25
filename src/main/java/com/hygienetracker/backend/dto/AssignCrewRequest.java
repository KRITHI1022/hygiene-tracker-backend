package com.hygienetracker.backend.dto;

import lombok.Data;

@Data
public class AssignCrewRequest {
    private String crewName;
    private Long officialId;
}