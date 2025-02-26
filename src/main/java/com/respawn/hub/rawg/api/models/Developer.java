package com.respawn.hub.rawg.api.models;

import com.respawn.hub.rawg.api.records.developers.RawgDevelopersDTO;

public class Developer {
    private Integer id;
    private String name;
    private String slug;
    public Developer (RawgDevelopersDTO developer) {
        id = developer.id();
        name = developer.name();
        slug = developer.slug();
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSlug() {
        return slug;
    }
    public void setSlug(String slug) {
        this.slug = slug;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Developer other = (Developer) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Developer\nid=" + id + ",\nname=" + name + ",\nslug=" + slug;
    }
    
}
