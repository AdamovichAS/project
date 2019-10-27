package com.github.adamovichas.project.model.dto;




public class TeamDTO {

    private Long id;
    private Long idLeague;
    private String name;

    public TeamDTO(Long id, Long idLeague, String name) {
        this.id = id;
        this.idLeague = idLeague;
        this.name = name;
    }

    public Long getIdLeague() {
        return idLeague;
    }

    public void setIdLeague(Long idLeague) {
        this.idLeague = idLeague;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
