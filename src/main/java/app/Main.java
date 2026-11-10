import com.github.lalyos.jfiglet.FigletFont;

import static java.lang.IO.println;

void main(){
    try {
        println(FigletFont.convertOneLine("RossiGo"));
    } catch (Exception e) {
        println("     ROSSIGO     ");
    }
}