import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public final class GameOfLife {

        public final static void main(String[] args) throws IOException {

        GameOfLife gof = new GameOfLife();

        Set<int []> dead_neighbour_cells = new HashSet<int []>();
        Set<int []> final_result = new HashSet<int []>();

        int [][] live_cells = gof.get_live_cells();
        for (int [] cell: live_cells) {
            int live_count = 0;
            System.out.print(Arrays.toString(cell) + '\n');
            int[][] neighbour_cells = gof.get_neighbour_cells(cell);
            for (int [] cell_neighbour: neighbour_cells) {
                if (gof.check_available_in_array(cell_neighbour, live_cells)) {
                    live_count += 1;
                } else {
                    dead_neighbour_cells.add(cell_neighbour);
                }
            }
            System.out.print('\n');
            if (1 < live_count && live_count < 4) {
                final_result.add(cell);
            }
        }
        for (int [] dead_neighbour_cell: dead_neighbour_cells) {
            int[][] neighbour_cells = gof.get_neighbour_cells(dead_neighbour_cell);
            int live_count_new = 0;
            for (int [] cell_neighbour: neighbour_cells) {
                if (gof.check_available_in_array(cell_neighbour, live_cells)) {
                    live_count_new += 1;
                }
            }
            if (live_count_new == 3) {
                
                if (!gof.check_available_in_set(dead_neighbour_cell, final_result)) {
                    final_result.add(dead_neighbour_cell);
                }
                
            }
        }

        System.out.print("Final Cell: " + '\n');
        int no_value = 0;
        for (int [] final_cell: final_result) {
            no_value = 1;
            System.out.print(Arrays.toString(final_cell));
        }
        if (no_value == 0) {
            System.out.print("No Active Cells");
        }
    }

    public boolean check_available_in_array(int[] cell, int [][] set_passed) {
        for (int[] single_cell: set_passed) {
            if (Arrays.equals(cell, single_cell)) {
                return true;
            }
        }
        return false;
    }

    public boolean check_available_in_set(int[] cell, Set<int []> array_list_passed) {
        for (int[] single_cell: array_list_passed) {
            if (Arrays.equals(cell, single_cell)) {
                return true;
            }
        }
        return false;
    }

    public int[][] get_live_cells() throws IOException {
        System.out.println("Input list: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input_value = reader.readLine();

        String[] co_ordinate_list = input_value.split(" ", 0);
        int [][] final_live_cells = new int [co_ordinate_list.length][2];
        int outer_count = 0;
        for (String co_ordinate: co_ordinate_list) {
            String[] x_y_split = co_ordinate.split(",", 0);
            int [] co_ordinate_int = new int[2];
            int inner_count = 0;
            for (String x_y: x_y_split) {
                co_ordinate_int[inner_count] = Integer.parseInt(x_y);
                inner_count +=1;
            }
            final_live_cells[outer_count] = co_ordinate_int;
            outer_count += 1;
        }
        // int [][] input_live_cells = {{1, 2}, {2, 3}};
        return final_live_cells;
    }

    public int [][] get_neighbour_cells(int [] cell) {
        int [][] final_neighbour_cells = new int[8][2];
        int i = 0;
        for (int x=(cell[0] - 1); x < (cell[0] + 2); x++) {
            for (int y=(cell[1]-1); y < (cell[1] + 2); y++) {
                if (x == cell[0] && y == cell[1]) {
                    continue;
                }
                final_neighbour_cells[i][0] = x;
                final_neighbour_cells[i][1] = y;
                i += 1;
            }
        }
        return final_neighbour_cells;
    }
}