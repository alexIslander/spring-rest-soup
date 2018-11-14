package com.islander.islanderjsoup.model;

import com.islander.islanderjsoup.model.enumtype.CourtTye;
import com.islander.islanderjsoup.model.enumtype.SurfaceType;
import com.islander.islanderjsoup.model.enumtype.TennisCategoryType;

public class Attirbutes {
    TennisCategoryType type;
    SurfaceType surface;
    CourtTye court;

    public Attirbutes(TennisCategoryType type, SurfaceType surface, CourtTye court) {
        this.type = type;
        this.surface = surface;
        this.court = court;
    }

    public TennisCategoryType getType() {
        return type;
    }

    public void setType(TennisCategoryType type) {
        this.type = type;
    }

    public SurfaceType getSurface() {
        return surface;
    }

    public void setSurface(SurfaceType surface) {
        this.surface = surface;
    }

    public CourtTye getCourt() {
        return court;
    }

    public void setCourt(CourtTye court) {
        this.court = court;
    }
}
