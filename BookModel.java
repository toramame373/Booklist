import java.io.*;
import java.util.ArrayList;

public class BookModel {

    public ArrayList<BookData> bookList = new ArrayList<BookData>(); //それぞれ本の情報を持ったリストとなるBookDataクラスの動的配列

    BookModel() {

        try(BufferedReader buffReader = new BufferedReader(new FileReader("book.txt"));){

            try {
                String str = buffReader.readLine();

                while(str != null) {
                    String[] data = str.split("、",-1); //文字列を"、"を区切り文字にして分割する

                    for(int i = 1; i < 4; i++) { //書名以外の箇所に空のデータが入っていた場合は"不明"を格納する
                        if(data[i].equals("")) {
                            data[i] = "不明";
                        }
                    }

                    bookList.add(new BookData(data[0], data[1], data[2], data[3]));
                    str = buffReader.readLine();
                }

                buffReader.close();
            }
            catch(Exception e) {
                System.out.println("Error in inputting data of books");
                System.exit(0);
            }

        }
        catch(IOException e){
            System.out.println("Read Error of the file: book.txt");
            System.exit(0);
        }
    }

    void addBookList(String na, String au, String pu, String is) { //リストにデータを追加するメソッド
        bookList.add(new BookData(na, au, pu, is));
    }

    ArrayList<Integer> searchBook(String data, String words) { //dataで指定したカテゴリーでwordsが含まれる本の番号を抽出した配列を返すメソッド

        ArrayList<Integer> checkList = new ArrayList<Integer>();

        switch (data) {
            case "name": {
                for (int i = 0; i < bookList.size(); i++) {

                    if (bookList.get(i).getName().contains(words)) {
                        checkList.add(i);
                    }
                }
                break;
            }

            case "author": {
                for (int i = 0; i < bookList.size(); i++) {

                    if (bookList.get(i).getAuthor().contains(words)) {
                        checkList.add(i);
                    }
                }
                break;
            }

            case "publisher": {
                for (int i = 0; i < bookList.size(); i++) {

                    if (bookList.get(i).getPublisher().contains(words)) {
                        checkList.add(i);
                    }
                }
                break;
            }

            case "isbn": {
                for (int i = 0; i < bookList.size(); i++) {

                    if (bookList.get(i).getIsbn().contains(words)) {
                        checkList.add(i);
                    }
                }
                break;
            }
        }

        return checkList;
    }

    ArrayList<Integer> andSearch(ArrayList<Integer> list1, ArrayList<Integer> list2) { //二つの配列に一致した数値を格納した配列を返すメソッド
        ArrayList<Integer> andList = new ArrayList<Integer>();

        if(list1 != null) {

            if(list2 != null) {

                for (Integer list : list1) {

                    for (Integer value : list2) {

                        if (list.equals(value)) {
                            andList.add(value);
                            break;
                        }
                    }
                }
                return andList; //list1 != null && list2 != null
            }
            else {
                return list1; //list1 != null && list2 == null
            }
        }
        else {
            return list2; // list1 == null
        }
    }

    ArrayList<Integer> orSearch(ArrayList<Integer> list1, ArrayList<Integer> list2) { //二つの配列のどちらか一方にでも含まれている数値を格納した配列を返すメソッド
        ArrayList<Integer> orList = new ArrayList<Integer>();

        if(list1 != null) {

            if(list2 != null) {

                for (Integer integer : list1) {

                    for (int j = 0; j < list2.size(); j++) {

                        if (integer.equals(list2.get(j))) {
                            list2.remove(j);
                            break;
                        }
                    }
                }

                list1.addAll(list2);
            }
            return list1;
        }
        else {
            return list2; // list1 == null
        }
    }

    public String getBookList(String data) { //dataで指定したカテゴリーの情報の一覧を持った文字列を返すメソッド
        String listData = "<html><body>";

        switch (data) {
            case "name": {

                listData += "書名<br /><br />";

                for (BookData bookData : bookList) {
                    listData += bookData.getName() + "<br />";
                }
                break;
            }

            case "author": {

                listData += "著者名<br /><br />";

                for (BookData bookData : bookList) {
                    listData += bookData.getAuthor() + "<br />";
                }
                break;
            }

            case "publisher": {

                listData += "出版社<br /><br />";

                for (BookData bookData : bookList) {
                    listData += bookData.getPublisher() + "<br />";
                }
                break;
            }

            case "isbn": {

                listData += "ISBN<br /><br />";

                for (BookData bookData : bookList) {
                    listData += bookData.getIsbn() + "<br />";
                }
                break;
            }
        }

        listData += "</body></html>";
        return listData;
    }

    public String getBookList(String data, ArrayList<Integer> list) { //listに格納された番号の本の情報のみを持った文字列を返すメソッド
        String listData = "<html><body>";

        switch (data) {
            case "name": {

                listData += "書名<br /><br />";

                for (Integer integer : list) {
                    listData += bookList.get(integer).getName() + "<br />";
                }
                break;
            }

            case "author": {

                listData += "著者名<br /><br />";

                for (Integer integer : list) {
                    listData += bookList.get(integer).getAuthor() + "<br />";
                }
                break;
            }

            case "publisher": {

                listData += "出版社<br /><br />";

                for (Integer integer : list) {
                    listData += bookList.get(integer).getPublisher() + "<br />";
                }
                break;
            }

            case "isbn": {

                listData += "ISBN<br /><br />";

                for (Integer integer : list) {
                    listData += bookList.get(integer).getIsbn() + "<br />";
                }
                break;
            }
        }

        listData += "</body></html>";
        return listData;
    }

    public void writeBookList() { //book.txtにデータリストを書き込むメソッド
        String writeData;

        try(BufferedWriter buffWriter = new BufferedWriter(new FileWriter("book.txt"))){
            for (BookData bookData : bookList) {

                if(bookData.getAuthor().equals("")) { //書名以外の箇所に空のデータが入っていた場合は"不明"を記述する
                    bookData.setAuthor("不明");
                }
                if(bookData.getPublisher().equals("")) {
                    bookData.setPublisher("不明");
                }
                if(bookData.getIsbn().equals("")) {
                    bookData.setIsbn("不明");
                }

                writeData = bookData.getName() + "、" + bookData.getAuthor() + "、" + bookData.getPublisher() + "、" + bookData.getIsbn();
                buffWriter.write(writeData);
                buffWriter.newLine();
            }
        }
        catch(IOException e){
            System.out.println("Write Error");
        }
    }

    public int getBookListNum() {
        return bookList.size();
    }
}
