package co.edu.uptc.model;

import java.util.ArrayList;

public class RouteBuilder {
    private ArrayList<Integer> coorX;
    private ArrayList<Integer> coorY;

    public RouteBuilder(){
        coorX = new ArrayList<>();
        coorY = new ArrayList<>();
    }

    public void setByPoint(int coorX, int coorY){
        if (this.coorX.isEmpty()){
            this.coorX.add(this.coorX.size(), coorX);
            this.coorY.add(this.coorY.size(), coorY);
        }else{
            this.coorX.add(this.coorY.size()-1, coorX);
            this.coorY.add(this.coorY.size()-1, coorY);
        }
    }

    public void fillRouteGaps() {
        int lastPointX = coorX.get(coorX.size() - 1);
        int lastPointY = coorY.get(coorY.size() - 1);
        int currentX = coorX.get(0);
        int currentY = coorY.get(0);
        int nextX = coorX.get(1);
        int nextY = coorY.get(1);
        int count = 0;
        while (currentX != lastPointX || currentY != lastPointY) {
            while (currentX != nextX || currentY != nextY) {
                currentX = currentX == nextX? currentX : currentX < nextX ? currentX + 1 : currentX - 1;
                currentY = currentY == nextY? currentY : currentY < nextY ? currentY + 1 : currentY - 1;
                coorX.add(currentX);
                coorY.add(currentY);
            }
            count += 1;
            nextX = coorX.get(count);
            nextY = coorY.get(count);
        }
    }

    public int[] getPoint(int position){
        if (coorX.size()-1 <= position){
            return new int[]{-1, coorX.get(coorX.size()-1), coorY.get(coorY.size()-1)};
        }
        return new int[]{coorX.get(position), coorY.get(position)};
    }
}
