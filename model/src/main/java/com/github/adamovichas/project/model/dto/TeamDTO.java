package com.github.adamovichas.project.model.dto;




public class TeamDTO {

    private String name;
 //   private Long idLeague;

    public TeamDTO(String name) {
 //       this.idLeague = idLeague;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }
}
