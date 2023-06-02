import java.awt.*;
import java.awt.event.*;
import java.awt.print.Book;
import java.util.ArrayList;
import javax.swing.*;

public class BookView extends JFrame implements ActionListener  {

    private BookCtrl ctrl;
    private Container c;
    private JTextField in1, in2, in3, in4, ser1, ser2, ser3, ser4;
    private JLabel  l1, l2, l3, l4, ll1, ll2, ll3, ll4, l5, l6, lnum;
    private JButton b1, b2, b3, b4;
    private JCheckBox cb1;
    private JPanel pAdd, pAddText, pView, pSearch, pSearchText;
    //private JScrollPane scrollPane;

    public BookListView blv;

    public BookView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setTitle("BookView");

        ctrl = new BookCtrl();
        c = getContentPane();

        //入力フィールド
        pAdd = new JPanel();
        pAdd.setPreferredSize(new Dimension(850, 100));

        l5 = new JLabel("追加機能");
        b1 = new JButton("追加する");

        pAddText = new JPanel();
        pAddText.setPreferredSize(new Dimension(850, 50));
        l1 = new JLabel("書名");
        l2 = new JLabel("著者名");
        l3 = new JLabel("出版社");
        l4 = new JLabel("ISBN");
        in1 = new JTextField("", 16);
        in2 = new JTextField("", 16);
        in3 = new JTextField("", 16);
        in4 = new JTextField("", 16);

        pAddText.setLayout(new GridLayout(2,4));
        pAddText.add(l1);
        pAddText.add(l2);
        pAddText.add(l3);
        pAddText.add(l4);
        pAddText.add(in1);
        pAddText.add(in2);
        pAddText.add(in3);
        pAddText.add(in4);

        pAdd.setLayout(new BorderLayout());
        pAdd.add(l5, BorderLayout.NORTH);
        pAdd.add(pAddText, BorderLayout.CENTER);
        pAdd.add(b1, BorderLayout.SOUTH);


        //表示フィールド
        pView = new JPanel();
        pView.setPreferredSize(new Dimension(950, 550));

        lnum = new JLabel("表示件数：" + "" + "件");

        blv = new BookListView();
        b2 = new JButton("書籍一覧を表示する");

        /*
        scrollPane = new JScrollPane(blv);
        scrollPane.setPreferredSize(new Dimension(15, 450));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
         */

        pView.setLayout(new BorderLayout());
        pView.add(lnum, BorderLayout.NORTH);
        pView.add(blv, BorderLayout.CENTER);
        //pView.add(scrollPane, BorderLayout.EAST);
        pView.add(b2, BorderLayout.SOUTH);

        //初期表示
        String num = String.valueOf(ctrl.getBookListNum());
        lnum.setText("表示件数：" + num + "件");
        blv.setListName(ctrl.getBookList("name"));
        blv.setListAuthor(ctrl.getBookList("author"));
        blv.setListPublisher(ctrl.getBookList("publisher"));
        blv.setListIsbn(ctrl.getBookList("isbn"));


        //検索フィールド
        pSearch = new JPanel();
        pSearch.setPreferredSize(new Dimension(850, 100));

        l6 = new JLabel("検索機能（あいまい検索は書名欄のキーワードで行います）");
        cb1 = new JCheckBox("あいまい検索");
        b3 = new JButton("検索結果を表示する");

        pSearchText = new JPanel();
        pSearchText.setPreferredSize(new Dimension(850, 50));
        ll1 = new JLabel("書名");
        ll2 = new JLabel("著者名");
        ll3 = new JLabel("出版社");
        ll4 = new JLabel("ISBN");
        ser1 = new JTextField("", 16);
        ser2 = new JTextField("", 16);
        ser3 = new JTextField("", 16);
        ser4 = new JTextField("", 16);

        pSearchText.setLayout(new GridLayout(2,4));
        pSearchText.add(ll1);
        pSearchText.add(ll2);
        pSearchText.add(ll3);
        pSearchText.add(ll4);
        pSearchText.add(ser1);
        pSearchText.add(ser2);
        pSearchText.add(ser3);
        pSearchText.add(ser4);

        pSearch.setLayout(new BorderLayout());
        pSearch.add(l6, BorderLayout.NORTH);
        pSearch.add(pSearchText, BorderLayout.CENTER);
        pSearch.add(cb1, BorderLayout.EAST);
        pSearch.add(b3, BorderLayout.SOUTH);


        b4 = new JButton("リストの変更を保存して終了する");

        c.setLayout(new FlowLayout());
        c.add(pAdd);
        c.add(pView);
        c.add(pSearch);
        c.add(b4);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        cb1.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == b1) { //本をリストに追加する

            String name = in1.getText();
            String author = in2.getText();
            String publisher = in3.getText();
            String isbn = in4.getText();

            l5.setText("追加機能　　　　　" + ctrl.addBookList(name, author, publisher, isbn));
        }

        else if(e.getSource() == b2) { //全書籍の一覧を表示する

            String num = String.valueOf(ctrl.getBookListNum());
            lnum.setText("表示件数：" + num + "件");
            blv.setListName(ctrl.getBookList("name"));
            blv.setListAuthor(ctrl.getBookList("author"));
            blv.setListPublisher(ctrl.getBookList("publisher"));
            blv.setListIsbn(ctrl.getBookList("isbn"));
        }

        else if(e.getSource() == b3) { //検索する

            String name = ser1.getText();
            String author = ser2.getText();
            String publisher = ser3.getText();
            String isbn = ser4.getText();
            ArrayList<Integer> checkList;

            if(cb1.isSelected()) { //チェックボックス：あいまい検索

                checkList = ctrl.searchBookList(name);
            }
            else {

                checkList = ctrl.searchBookList(name, author, publisher, isbn);
            }

            String num = String.valueOf(checkList.size());
            lnum.setText("検索結果：" + num + "件");
            blv.setListName(ctrl.getBookList("name", checkList));
            blv.setListAuthor(ctrl.getBookList("author", checkList));
            blv.setListPublisher(ctrl.getBookList("publisher", checkList));
            blv.setListIsbn(ctrl.getBookList("isbn", checkList));
        }

        else if(e.getSource() == b4) { //リストの変更を保存して終了する

            ctrl.writeBookList();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        BookView bv = new BookView();

        bv.setVisible(true);
    }
}
