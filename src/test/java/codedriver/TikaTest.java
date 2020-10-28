package codedriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.parser.microsoft.ooxml.OOXMLParser;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.parser.txt.TXTParser;
import org.apache.tika.parser.xml.XMLParser;
import org.apache.tika.sax.BodyContentHandler;
import org.junit.Test;
import org.xml.sax.SAXException;

public class TikaTest {

    @Test
    /**
    * @Author 89770
    * @Time 2020年10月27日  
    * @Description: 测试获取文件类型
    * @Param 
    * @return
     */
    public void typeDetection() throws IOException {
        //assume example.mp3 is in your current directory
        File file = new File("C:\\Users\\89770\\Desktop\\desktop\\inti1.sql");//
        //Instantiating tika facade class 
        Tika tika = new Tika();
        //detecting the file type using detect method
        String filetype = tika.detect(file);
        System.out.println(filetype);
    }
    
    @Test
    /**
    * @Author 89770
    * @Time 2020年10月27日  
    * @Description: 测试获取文件内容
    * @Param 
    * @return
     */
    public void getContent() throws IOException, TikaException {
        //assume example.mp3 is in your current directory
        File file = new File("C:\\Users\\89770\\Desktop\\desktop\\金科二期\\ITSM新需求20200107.xlsx");//
        //Instantiating tika facade class 
        Tika tika = new Tika();
        //detecting the file type using detect method
        String filecontent = tika.parseToString(file);
        System.out.println(filecontent);
    }
    
    @Test
    /**
    * @Author 89770
    * @Time 2020年10月27日  
    * @Description: 测试获取文件内容
    * @Param 
    * @return
     */
    public void getContentByAutoParser() throws IOException, TikaException, SAXException {
        //detecting the file type
          BodyContentHandler handler = new BodyContentHandler();
          Metadata metadata = new Metadata();
          FileInputStream inputstream = new FileInputStream(new File("C:\\Users\\89770\\Desktop\\desktop\\金科二期\\前期需求规划\\金科二期.pptx"));
          //Text document parser
          AutoDetectParser  TexTParser = new AutoDetectParser();
          TexTParser.parse(inputstream, handler, metadata);
          System.out.println("Contents of the document:" + handler.toString().replaceAll("\\s{2,}|\t|\n", ""));
          System.out.println("Metadata of the document:");
          String[] metadataNames = metadata.names();
          for(String name : metadataNames) {
             System.out.println(name + " : " + metadata.get(name));
          }
      }
    
    @Test
    /**
    * @Author 89770
    * @Time 2020年10月27日  
    * @Description: 测试获取EXCEL内容
    * @Param 
    * @return
     */
    public void getExcelContent() throws IOException, TikaException, SAXException {
        BodyContentHandler handler = new BodyContentHandler();  
        Metadata metadata = new Metadata();  
        FileInputStream inputstream = new FileInputStream(new File("C:\\Users\\89770\\Desktop\\desktop\\金科二期\\ITSM新需求20200107.xlsx"));  
        ParseContext pcontext = new ParseContext();  
        OOXMLParser  msofficeparser = new OOXMLParser ();   
        msofficeparser.parse(inputstream, handler, metadata,pcontext);  
        System.out.println("Contents of the document:" + handler.toString().replaceAll("\\s{2,}|\t|\n", ""));
        System.out.println("Metadata of the document:"); 
        String[] metadataNames = metadata.names();  
        for(String name : metadataNames) {  
           System.out.println(name + ": " + metadata.get(name) );  
        }  
    }
    
    @Test
    /**
    * @Author 89770
    * @Time 2020年10月27日  
    * @Description: 测试获取PDF内容
    * @Param 
    * @return
     */
    public void getPdfContent() throws IOException, TikaException, SAXException {
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(new File("C:\\Users\\89770\\Desktop\\desktop\\04403190011179759468.pdf"));
        ParseContext pcontext = new ParseContext();
        //parsing the document using PDF parser
        PDFParser pdfparser = new PDFParser(); 
        pdfparser.parse(inputstream, handler, metadata,pcontext);
        //getting the content of the document
        System.out.println("Contents of the PDF :" + handler.toString());
        //getting metadata of the document
        System.out.println("Metadata of the PDF:");
        String[] metadataNames = metadata.names();
        for(String name : metadataNames) {
           System.out.println(name+ " : " + metadata.get(name));
        }
    }
    
    @Test
    /**
    * @Author 89770
    * @Time 2020年10月27日  
    * @Description: 测试获取TXT内容
    * @Param 
    * @return
     */
    public void getTxtContent() throws IOException, TikaException, SAXException {
      //detecting the file type
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(new File("C:\\Users\\89770\\Desktop\\desktop\\codedriver表单及流程问题.txt"));
        ParseContext pcontext=new ParseContext();
        //Text document parser
        TXTParser  TexTParser = new TXTParser();
        TexTParser.parse(inputstream, handler, metadata,pcontext);
        System.out.println("Contents of the document:" + handler.toString());
        System.out.println("Metadata of the document:");
        String[] metadataNames = metadata.names();
        for(String name : metadataNames) {
           System.out.println(name + " : " + metadata.get(name));
        }
    }
    
    @Test
    /**
    * @Author 89770
    * @Time 2020年10月27日  
    * @Description: 测试获取HTML内容
    * @Param 
    * @return
     */
    public void getHTMLContent() throws IOException, TikaException, SAXException {
        //detecting the file type
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(new File("C:\\Users\\89770\\Desktop\\desktop\\虚拟机实施反馈.html"));
        ParseContext pcontext = new ParseContext();
        //Html parser 
        HtmlParser htmlparser = new HtmlParser();
        htmlparser.parse(inputstream, handler, metadata,pcontext);
        System.out.println("Contents of the document:" + handler.toString());
        System.out.println("Metadata of the document:");
        String[] metadataNames = metadata.names();
        for(String name : metadataNames) {
           System.out.println(name + ":   " + metadata.get(name));  
        }
    }
    
    @Test
    /**
    * @Author 89770
    * @Time 2020年10月27日  
    * @Description: 测试获取XML内容
    * @Param 
    * @return
     */
    public void getXMLContent() throws IOException, TikaException, SAXException {
        //detecting the file type
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(new File("C:\\Users\\89770\\git\\codedriver-test\\pom.xml"));
        ParseContext pcontext = new ParseContext();
        //Xml parser
        XMLParser xmlparser = new XMLParser(); 
        xmlparser.parse(inputstream, handler, metadata, pcontext);
        System.out.println("Contents of the document:" + handler.toString());
        System.out.println("Metadata of the document:");
        String[] metadataNames = metadata.names();
        for(String name : metadataNames) {
           System.out.println(name + ": " + metadata.get(name));
        }
    }
    
    
}
