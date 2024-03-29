import java.util.TreeMap;
import java.util.Vector;
import java.io.*;

/**
 * author: fangwei
 * date  : 2020-04-07  
 */
public class PrefixTree{

    /**定义节点类 */
    public class Node{
        public TreeMap<Character,Node> next= new TreeMap();
        public boolean IsAbbr;

        public Node(){
            this.IsAbbr=false;
        }
    }

    /**定义字典树对象的成员变量 */
    public Node root;
    public int size=0;

    public PrefixTree(){
        root = new Node();
        this.CreatePrefixTree();                          // 构造函数在创建对象的时候自动读取文件创建树对象
    }

    /**字典树添加节点 */
    public void AddAbbr(String abbr){
        Node current=this.root;
        for(int i=0;i<abbr.length();i++){
            if(current.next.get(abbr.charAt(i))==null){
                current.next.put(abbr.charAt(i),new Node());
            }
            current=current.next.get(abbr.charAt(i));
        }
        current.IsAbbr=true;
        this.size=this.size+1;
    }

    /**创建字典树对象 */
    public void CreatePrefixTree(){
        String encoding="UTF-8";
        String filePath="D:\\software\\CODE\\JAVA\\PrefixTree\\ProjectAbbr.txt";
        File file=new File(filePath);
       try{
            InputStreamReader rd = new InputStreamReader(new FileInputStream(file),encoding);
            BufferedReader br = new BufferedReader(rd);
            String br_line;
            while((br_line=br.readLine())!=null){
                this.AddAbbr(br_line.toUpperCase());        // 创建树对象时统一转换为大写
            }
            br.close();
            
        } catch(Exception e) {
            System.out.println("读取文件内容出错");
        } finally {
        }
    }

    /**查询整个字符串是否包含字典树中的关键字 */
    public String FindAbbr(String abbr){
        String ABBR=abbr.toUpperCase();                   // 查询时字符串统一转换为大写
        String result=null;
        int i=0;                                          
        int j=0;
        Vector<Integer> start=new Vector<Integer>();     // 使用vector用于存储多个关键字的起始位置
        Vector<Integer> end=new Vector<Integer>();       // 使用vector用于存储多个关键字的结束位置
        Node current=null;                               // 当前节点用于表示查询时字典树中指针的位置
        for(;i<abbr.length();){
            int m=-1;
            int n=-1;
            current=this.root;
            if(current.next.get(ABBR.charAt(i))!=null){
                current=current.next.get(ABBR.charAt(i));
                if(current.IsAbbr){
                    m=i;
                    n=i;
                }
                for(j=i+1;j<abbr.length();j++){
                    if(current.next.get(ABBR.charAt(j))!=null){
                        current=current.next.get(ABBR.charAt(j));
                        if(current.IsAbbr){
                            m=i;
                            n=j;
                        }
                    }else{
                        break;
                    }
                }
                if((n!=-1)&(m!=-1)){
                    start.add(new Integer(m));
                    end.add(new Integer(n));
                    i=j+1;
                }else{
                    i=i+1;
                }
            }else{
                i=i+1;
            }
        }
        if(start.size()>1){
            result="ERROR1:MORE THAN ONE ABBR";
        }else if(start.size()<=0){
            result="ERROR2:NO ABBR";
        }else{
            result=ABBR.substring(start.elementAt(0).intValue(), end.elementAt(0).intValue()+1);
        }
        return result;
    }
    public static void main(String []args){
        /*
        PrefixTree tree= new PrefixTree();
        System.out.println(tree.FindAbbr("credit_cust.aitmp_cc_ai_cq_01"));
        */
        String out_pathname="D:\\software\\CODE\\JAVA\\PrefixTree\\AbbrRes.txt";
        File AbbrFile=new File(out_pathname);
        BufferedWriter out=null;

        try{
            if(!AbbrFile.exists()){
                AbbrFile.createNewFile();
            }else{
                AbbrFile.delete();
                AbbrFile.createNewFile();
            }
            out=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(AbbrFile), "UTF-8"));
            out.write("test1");
            out.newLine();
            out.write("test2");
            out.flush();
            out.close();
        }catch(Exception e){
            System.out.println("文件已存在");

        }finally{

        }
    }
}    