package MiSS;

/**
 * Created by Patrycjaa on 2016-11-28.
 */
public class HWIND extends AbstractTreeModel{
    
    /**
     *
     * @param tree - struktura opisujaca drzewo
     * @param speed - predkosc wiatru oddzialujaca na drzewo
     * @param treeI - polozenie drzewa na macierzy X
     * @param treeJ - polozenie drzewa na macierzy X
     * @return 0 if drzewo stoi, 1 if drzewo zlamane, 2 if drzewo przewrocone
     */
    @Override
    public double calculateTreeForce(double speed){
        return 0;
    }

    /**
     *
     * @param treeI - polozenie drzewa na macierzy X
     * @param treeJ - polozenie drzewa na macierzy X
     * @return odleglosc drzewa od brzegu macierzy X
     */
    double calculateDistanceFromForestEdge(int treeI, int treeJ){
        return 0;
    }
    /**
     * pole powierzchni korony na danym odcinku i
     */
    double triangleSectorArea(Tree tree, int i){
        double xleft_base=0,xleft_top=0,xright_base=0,xright_top=0,xmiddle=0,a=0,b=0,c=0,h1=0,h2=0,area=0;
        double height = tree.Crown_depth;
        double width = tree.Crown_width;
        double dx = width/height;
        double xleft = 0;
        double xright = width;

        xmiddle = width/2;
        if (i < Math.floor(height/2)){
            xleft_top = xmiddle - i*dx;
            xright_top = xmiddle + i*dx;

            xleft_base = xmiddle - (i+1)*dx;
            xright_base = xmiddle + (i+1)*dx;
        } else if (i >= Math.ceil(height/2)){
            xleft_top = xleft + (i - height/2)*dx;
            xright_top = xright - (i - height/2)*dx;

            xleft_base = xleft + (i + 1 - height/2)*dx;
            xright_base = xright - (i + 1 - height/2)*dx;
        } else {
            xleft_base = xmiddle - i*dx;
            xright_base = xmiddle + i*dx;

            xleft_top = xleft + (i + 1 - height/2)*dx;
            xright_top = xright - (i + 1 - height/2)*dx;

            a = xright_base - xleft_base;
            b = width;
            c = xright_top - xleft_top;

            h1 = height/2 - i;
            h2 = i + 1 - height/2;

            area = ((a+b)/2)*h1 + ((b+c)/2)*h2;
            return area;
        }
        a = xright_top - xleft_top;
        b = xright_base - xleft_base;

        area = (a+b)/2;
        return area;
    }
}
