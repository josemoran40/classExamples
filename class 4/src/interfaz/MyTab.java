/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

/**
 *
 * @author Emely
 */
public class MyTab extends JPanel {

    RSyntaxTextArea textArea;
    private String path;

    public MyTab() {
        super(new BorderLayout());
        textArea = new RSyntaxTextArea(20, 60);
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        textArea.setCurrentLineHighlightColor(Color.WHITE);
        textArea.setCaretColor(Color.BLACK);
        textArea.setFont(new Font("monospaced", Font.PLAIN, 16));
        //configurando el textArea
//        AbstractTokenMakerFactory atmf = (AbstractTokenMakerFactory) TokenMakerFactory.getDefaultInstance();
//        atmf.putMapping("text/C3D", "miejecutorc3d.SyntaxC3D");
//        textArea.setSyntaxEditingStyle("text/C3D");
//        textArea.setCurrentLineHighlightColor(Color.WHITE);
//        textArea.setCodeFoldingEnabled(true);
//        textArea.setFont(new Font("monospaced", Font.PLAIN, 16));
//        SyntaxScheme scheme = textArea.getSyntaxScheme();
//        textArea.setCaretColor(Color.BLACK);
//        scheme.getStyle(Token.RESERVED_WORD).foreground = Color.BLUE;
//        scheme.getStyle(Token.FUNCTION).foreground = Color.BLUE;
//        scheme.getStyle(Token.LITERAL_STRING_DOUBLE_QUOTE).foreground = Color.ORANGE;
//        scheme.getStyle(Token.LITERAL_CHAR).foreground = Color.ORANGE;
//        scheme.getStyle(Token.IDENTIFIER).foreground = Color.BLACK;
//        scheme.getStyle(Token.DATA_TYPE).foreground = Color.BLUE;
//        scheme.getStyle(Token.COMMENT_EOL).foreground = Color.GRAY;
//        scheme.getStyle(Token.COMMENT_MULTILINE).foreground = Color.GRAY;
//        scheme.getStyle(Token.SEPARATOR).foreground = Color.RED;
//        scheme.getStyle(Token.SEPARATOR).foreground = Color.RED;

        //-------------------------
        RTextScrollPane sp = new RTextScrollPane(textArea);
        // this.setBounds(0, 0, (int) dim.getWidth(), (int) dim.getHeight());
        this.add(sp);
    }

    MyTab(String texto, String path) {
        super(new BorderLayout());
        textArea = new RSyntaxTextArea(20, 60);
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        textArea.setCurrentLineHighlightColor(Color.WHITE);
        textArea.setCaretColor(Color.BLACK);
        textArea.setFont(new Font("monospaced", Font.PLAIN, 16));
        //configurando el textArea
//        AbstractTokenMakerFactory atmf = (AbstractTokenMakerFactory) TokenMakerFactory.getDefaultInstance();
//        atmf.putMapping("text/C3D", "miejecutorc3d.SyntaxC3D");
//        textArea.setSyntaxEditingStyle("text/C3D");
//        textArea.setCurrentLineHighlightColor(Color.WHITE);
//        textArea.setCodeFoldingEnabled(true);
//        textArea.setFont(new Font("monospaced", Font.PLAIN, 16));
//        SyntaxScheme scheme = textArea.getSyntaxScheme();
//        textArea.setCaretColor(Color.BLACK);
//        scheme.getStyle(Token.RESERVED_WORD).foreground = Color.BLUE;
//        scheme.getStyle(Token.FUNCTION).foreground = Color.BLUE;
//        scheme.getStyle(Token.LITERAL_STRING_DOUBLE_QUOTE).foreground = Color.ORANGE;
//        scheme.getStyle(Token.LITERAL_CHAR).foreground = Color.ORANGE;
//        scheme.getStyle(Token.IDENTIFIER).foreground = Color.BLACK;
//        scheme.getStyle(Token.DATA_TYPE).foreground = Color.BLUE;
//        scheme.getStyle(Token.COMMENT_EOL).foreground = Color.GRAY;
//        scheme.getStyle(Token.COMMENT_MULTILINE).foreground = Color.GRAY;
//        scheme.getStyle(Token.SEPARATOR).foreground = Color.RED;
//        scheme.getStyle(Token.SEPARATOR).foreground = Color.RED;

        //-------------------------
        RTextScrollPane sp = new RTextScrollPane(textArea);
        // this.setBounds(0, 0, (int) dim.getWidth(), (int) dim.getHeight());
        this.add(sp);
        this.path = path;
        this.textArea.setText(texto);
    }

    String getText() {
        return textArea.getText();
    }

    boolean isEmptyText() {
        return this.textArea.getText().isEmpty();
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

}
