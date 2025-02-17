package ladder;

public class Node {
    private int state; // -1 : gotoLeft, 1 : gotoRight, 0 : gotoDown
    private int current_row;
    private int current_col;

    public Node(int startPoint){
        this.state = 0;
        this.current_row = 1;
        this.current_col = startPoint;
    }

    public int move(LineManager lineManager) {
        this.state = lineManager.nextDirection(this.current_row, this.current_col, this.state);
        if(state == 0){
            gotoDown();
        }
        followLine();
        return this.current_col;
    }

    private void gotoDown() {
        this.current_row = this.current_row + 1;
    }

    private void followLine() {
        this.current_col = this.current_col + this.state;
    }

    public boolean isRowExceedValue(int value) {
        validateValue(value);
        if(this.current_row > value)
            return false;
        return true;
    }

    private void validateValue(int value) {
        if(value < 0)
            throw new IllegalArgumentException("value는 음수가 될 수 없습니다.");
    }
}
