import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class MineSweeper {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        int row, col, mine, select, hp = 1;
        System.out.print("Mayın tarlasının boyutlarını giriniz: ");
        row = input.nextInt();
        col = input.nextInt();
        String[][] field = new String[row][col];
        String[][] mineField = new String[row][col];
        mine = (row * col) / 4;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                field[i][j] = "-";
                mineField[i][j] = "-";
            }
        }
        for (int i = 0; i < mine; i++) {
            int rowMine = rand.nextInt(row);
            int colMine = rand.nextInt(col);
            if (mineField[rowMine][colMine] == "*") {
                i -= 1;
            } else {
                mineField[rowMine][colMine] = "*";
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(mineField[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Mayınların Konumu");
        System.out.println();
        System.out.println("Mayın Tarlası Oyununa Hoşgeldiniz !");
        int winCount = 0;

        while (hp > 0) {
            for (int i = 0; i < row; i++) {
                String line = "";
                for (int j = 0; j < col; j++) {
                    line += field[i][j] + " ";
                }
                System.out.println(line);
            }
            System.out.println("Satır giriniz: ");
            int rowSel = input.nextInt();
            System.out.println("Sütun giriniz: ");
            int colSel = input.nextInt();
            if (rowSel >= row || colSel >= col) {
                System.out.println("Lütfen geçerli bir nokta giriniz.");
                continue;
            }
            if (mineField[rowSel][colSel] == "*") {
                System.out.println("Kaybettiniz");
                hp--;
            }
            if (field[rowSel][colSel] != "-") {
                System.out.println("Bu noktayı zaten seçtiniz! ");
                continue;
            }
            if (field[rowSel][colSel] == "-") {
                winCount++;
            }
            int count = 0;
            for (int i = rowSel - 1; i <= rowSel + 1; i++) {
                for (int j = colSel - 1; j <= colSel + 1; j++) {
                    if (i >= 0 && i < mineField.length && j >= 0 && j < mineField[i].length) {
                        if (i != rowSel || j != colSel) {
                            if (mineField[i][j] == "*") {
                                count++;
                            }
                        }
                    }
                }
            }
            field[rowSel][colSel] = String.valueOf(count);
            mineField[rowSel][colSel] = String.valueOf(count);
            if (winCount == (row * col) - mine) {
                System.out.println("KAZANDINIZ!");
                break;
            }
        }
    }
}
