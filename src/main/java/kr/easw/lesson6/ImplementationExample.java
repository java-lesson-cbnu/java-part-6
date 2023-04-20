package kr.easw.lesson6;

import javax.swing.*;
import java.awt.*;

/**
 * 해당 문제는 구현된 삼각형 코드를 보고, 사각형 그리기 코드를 작성해야 합니다.
 * <p>
 * 사각형은 정사각형으로 제한되지 않으며, 정상적인 사각형이 그려진다면 정답입니다.
 * <p>
 * 해당 문제는 다음의 조건을 갖습니다 :
 * - Rectangle 클래스를 생성해야 합니다. 생성한 클래스는 ShapeData를 상속받아야 합니다.
 * - Rectangle 클래스는 사각형을 그리는 코드를 포함해야 합니다.
 * - {@code ImplementationExample.getShape()}를 사정하여 Rectangle 클래스의 인스턴스를 반환하게끔 해야합니다.
 * <p>
 * 만약 문제가 너무 쉬우셨다면, 다음 조건도 시도해보세요.
 * 이 조건은 점수에 포함되지 않으며, Rectangle 대신 이 조건을 충족하여 제출하여도 정답 처리됩니다.
 * - 오각형을 그려보세요.
 * - 오각형 대신, 별 모양을 그려보세요.
 *   - 별 모양에 색을 칠해보세요. 색은 afterDraw 메서드를 상속하여 칠할 수 있습니다.
 */
public class ImplementationExample {

    public static void main(String[] args) {
        new ShapeWindow(new Triangle());
    }

    // 해당 메서드를 수정해야 합니다.
    public static ShapeData getShape() {
        return new Triangle();
    }

    private static class ShapeWindow extends JFrame {
        private final ShapeData shapeData;

        public ShapeWindow(ShapeData shapeData) {
            this.shapeData = shapeData;
            setSize(300, 300);
            setLayout(null);
            setVisible(true);
            Timer timer = new Timer(20, e -> {
                repaint();
            });
            timer.start();
        }

        @Override
        public void update(Graphics g) {
            paint(g);
        }

        @Override
        public void paint(Graphics g) {
            Graphics2D graphics2D = (Graphics2D) g;
            graphics2D.clearRect(0, 0, getHeight(), getWidth());
            graphics2D.setColor(new Color(0, 0, 0));
            shapeData.draw(graphics2D, new int[]{getWidth() / 2, getHeight() / 2});
        }

        public ShapeData getShapeData() {
            return shapeData;
        }
    }

    public static abstract class ShapeData {
        private double degree = 0.0;

        public abstract int getPointCounts();

        public abstract int[] createPosition(int point, int[] centre);

        public void draw(Graphics2D graphics, int[] centre) {
            degree += 0.5;
            if (degree >= 360.0) {
                degree = 0.0;
            }
            graphics.rotate(Math.toRadians(degree), centre[0], centre[1]);
            int[] x = new int[getPointCounts()];
            int[] y = new int[getPointCounts()];
            for (int i = 0; i < x.length; i++) {
                int[] locs = createPosition(i, centre);
                x[i] = locs[0];
                y[i] = locs[1];
            }
            for (int i = 0; i < getPointCounts(); i++) {
                int target = (i + 1 >= getPointCounts()) ? 0 : i + 1;
                graphics.drawLine(x[i], y[i], x[target], y[target]);
            }
            afterDraw(graphics, centre);
        }

        private void afterDraw(Graphics2D graphics2D, int[] centre) {
            // 상속에서의 구현을 위해 비워짐
        }
    }


    public static class Triangle extends ShapeData {
        @Override
        public int getPointCounts() {
            // 삼각형은 3개의 꼭짓점을 가지고 있기에, 3을 반환합니다.
            return 3;
        }

        @Override
        public int[] createPosition(int point, int[] centre) {
            // X / Y 2개를 반환해야함으로 2 크기의 배열을 만듭니다.
            // Centre는 화면의 중앙입니다.
            int[] positions = new int[2];
            switch (point) {
                case 0: {
                    // 0일 경우 윗쪽 곡짓점의 위치를 반환합니다.
                    positions[0] = centre[0];
                    positions[1] = centre[1] / 2;
                }
                break;
                case 1: {
                    // 1일 경우 왼쪽 아래 곡짓점의 위치를 반환합니다.
                    positions[0] = centre[0] / 2;
                    positions[1] = centre[1] + centre[1] / 2;
                }
                break;
                case 2: {
                    // 2일 경우 오른쪽 곡짓점의 위치를 반환합니다.
                    positions[0] = centre[0] + centre[0] / 2;
                    positions[1] = centre[1] + centre[1] / 2;
                }
                break;
            }

            return positions;
        }
    }

    public static class Rectangle extends ShapeData {

        @Override
        public int getPointCounts() {
            throw new RuntimeException("이 코드 라인을 지우고, 이곳에서 작성하십시오.");
        }

        @Override
        public int[] createPosition(int point, int[] centre) {
            throw new RuntimeException("이 코드 라인을 지우고, 이곳에서 작성하십시오.");
        }
    }

}
