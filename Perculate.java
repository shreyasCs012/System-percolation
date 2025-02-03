import java.util.Scanner;

public class Perculate {
    public static int n;
    public boolean[][] per;
    public int vertualtop;
    public int vertualbottom;
    WeightedQuickUnionUF uf;

    public Perculate(int n) {
        this.n = n;
        this.uf = new WeightedQuickUnionUF((n * n) + 2);
        per = new boolean[n][n];
        vertualtop = n * n;
        vertualbottom = (n * n) + 1;
    }

    public void open(int row, int col) {
        validate(row, col);
        if (isOpen(row, col)) {
            return;
        }
        per[row][col] = true;

        // Connect to virtual top if in top row
        if (row == 0) {
            uf.union(getIndex(row, col), vertualtop);
        }
        // Connect to virtual bottom if in bottom row
        if (row == n - 1) {
            uf.union(getIndex(row, col),vertualbottom);
        }
        // Connect to valid neighbors
        if (col > 0) {
            connectIfOpen(row, col, row, col - 1); // Left neighbor
        }
        if (col < n - 1) {
            connectIfOpen(row, col, row, col + 1); // Right neighbor
        }
        connectIfOpen(row, col, row - 1, col); // Top neighbor
        connectIfOpen(row, col, row + 1, col); // Bottom neighbor
    }


    public void validate(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            throw new IllegalArgumentException("Row or column out of bounds");
        }
    }

    public boolean isOpen(int row, int col) {
        return per[row][col];
    }

    public void connectIfOpen(int row1, int col1, int row2, int col2) {
        if (row2 >= 0 && row2 < n && col2 >= 0 && col2 < n && isOpen(row2, col2)) {
            uf.union(getIndex(row1, col1), getIndex(row2, col2));
        }
    }

    public int getIndex(int row, int col) {
        return row * n + col;
    }

    public boolean ifPercolates() {
        return (uf.find(vertualtop) == uf.find(vertualbottom));
    }

    private void Diplaypercolation() {
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print("|"+per[i][j]+"|\t");
            }
            System.out.println(" ");
        }
        uf.displyUF();
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int nu=0,no,row,col;
        System.out.println("Enter the dimension of the material");
        Perculate p=new Perculate(sc.nextInt());
        System.out.println("Enter the number of nodes to be opened");
        nu=sc.nextInt();
        System.out.println("Enter the node number  to be opened from 1 to "+n*n);
        for(int i=1 ;i<= nu;i++){
            no=sc.nextInt();
            row=(no-1)/n;
            col=(no-1)%n;
            p.open(row,col);
        }
        System.out.println(p.ifPercolates());
        p.Diplaypercolation();
    }
}
