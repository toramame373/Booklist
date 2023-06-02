import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BookListView extends JPanel { //本の一覧、検索結果の表示を行うパネルを扱うクラス

    private JPanel p;
    private JLabel listName, listAuthor, listPublisher, listIsbn;
    private LineBorder lineBorder;

    public BookListView() {

        p = new JPanel();
        p.setBackground(Color.white);
        p.setPreferredSize(new Dimension(930, 480));

        lineBorder = new LineBorder(Color.BLACK, 2, true);
        p.setBorder(lineBorder);

        listName = new JLabel("");
        listName.setPreferredSize(new Dimension(400, 480));
        listName.setVerticalAlignment(JLabel.TOP);
        listAuthor = new JLabel("");
        listAuthor.setPreferredSize(new Dimension(250, 480));
        listAuthor.setVerticalAlignment(JLabel.TOP);
        listPublisher = new JLabel("");
        listPublisher.setPreferredSize(new Dimension(100, 480));
        listPublisher.setVerticalAlignment(JLabel.TOP);
        listIsbn = new JLabel("");
        listIsbn.setPreferredSize(new Dimension(110,480));
        listIsbn.setVerticalAlignment(JLabel.TOP);

        //p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        //p.setLayout(new GridLayout(1,4));
        p.setLayout(new FlowLayout());
        p.add(listName);
        p.add(listAuthor);
        p.add(listPublisher);
        p.add(listIsbn);

        add(p); //BookListViewにp自身をaddしなければ表示されない

    }

    public void setListName(String list) {
        this.listName.setText(list);
    }
    public void setListAuthor(String list) {
        this.listAuthor.setText(list);
    }
    public void setListPublisher(String list) {
        this.listPublisher.setText(list);
    }
    public void setListIsbn(String list) {
        this.listIsbn.setText(list);
    }
}
