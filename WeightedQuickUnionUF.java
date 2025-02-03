public class WeightedQuickUnionUF {
    public int n;
    private static int[] uf;
    public WeightedQuickUnionUF(int n) {
        this.n = n;
        uf = new int[n];
        for(int i=0;i<n;i++){
            uf[i]=-1;
        }
    }

    public void union(int index1, int index2) {
        int root1 = find(index1);
        int root2 = find(index2);
        if (uf[root1] < uf[root2]) {
            uf[root1] -= uf[root2];
            uf[root2] = root1;
        }
        else if(uf[root1]==uf[root2]&&uf[root1]==-1) {
            uf[root1]=root2;
            uf[root2]-=1;
        }
        else {
            uf[root1]=root2;
            uf[root2]-=1;
        }
    }
    public int find(int index) {
        if(uf[index]<0){
            return index;
        }
           index=find(uf[index]);
           return index;
    }

    public void displyUF() {
        for(int i=0;i<n;i++){
            System.out.print(uf[i]+" ");
        }
    }

    public int size(int index){
        return (-(uf[find(index)]));
    }

    public boolean IsConnect(int index1,int index2){
        return find(index1) == find(index2);
    }
    public static void main(String[] args) {

        }
}

