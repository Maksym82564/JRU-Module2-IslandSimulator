package models.map;

import interfaces.Entity;

import java.util.Arrays;

public class IslandMap {
    private final int width;
    private final int height;
    private final Area[][] areas;

    public IslandMap(int width, int height) {
        this.width = width;
        this.height = height;
        areas = new Area[width][height];
        Arrays.stream(areas).forEach(row -> Arrays.setAll(row, i -> new Area()));
    }

    public void drawIsland() {
        for (int i = 0; i < areas.length; i++) {
            for (int j = 0; j < areas[i].length; j++) {
                System.out.print(areas[i][j].draw());
            }
            System.out.println();
        }
    }

    public Area getArea(Coordinates coords) {
        return areas[coords.getX()][coords.getY()];
    }

    public int getMaxX() {
        return areas.length - 1;
    }

    public int getMaxY() {
        return areas[0].length - 1;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Area[][] getAreas() {
        return areas;
    }
}