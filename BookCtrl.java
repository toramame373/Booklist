import java.util.ArrayList;

public class BookCtrl {

    BookModel mdl;

    BookCtrl() {
        mdl = new BookModel();
    }

    public String addBookList(String na, String au, String pu, String is) {

        if((!na.equals(""))) { //書名が入力されていればデータを登録する

            if(au.equals("")) {
                au = "不明";
            }
            if(pu.equals("")) {
                pu = "不明";
            }
            if(is.equals("")) {
                is = "不明";
            }

            mdl.addBookList(na, au, pu, is);
            return "新しい本「" + na + "」をリストに登録しました";
        }
        else {
            return "入力エラー：必ず書名は入力してください";
        }
    }

    public ArrayList<Integer> searchBookList(String na) { //あいまい検索
        ArrayList<Integer> checkList;
        ArrayList<Integer> authorCheckList = null;
        ArrayList<Integer> publisherCheckList = null;
        ArrayList<Integer> isbnCheckList = null;

        checkList = mdl.searchBook("name", na);
        authorCheckList = mdl.searchBook("author", na);
        publisherCheckList = mdl.searchBook("publisher", na);
        isbnCheckList = mdl.searchBook("isbn", na);

        if(authorCheckList != null) {

            checkList = mdl.orSearch(checkList, authorCheckList);
        }
        if(publisherCheckList != null) {

            checkList = mdl.orSearch(checkList, publisherCheckList);
        }
        if(isbnCheckList != null) {

            checkList = mdl.orSearch(checkList, isbnCheckList);
        }

        return checkList;
    }

    public ArrayList<Integer> searchBookList(String na, String au, String pu, String is) {
        ArrayList<Integer> checkList;
        ArrayList<Integer> authorCheckList = null;
        ArrayList<Integer> publisherCheckList = null;
        ArrayList<Integer> isbnCheckList = null;

        checkList = mdl.searchBook("name", na);

        if(!au.equals("")) {

            authorCheckList = mdl.searchBook("author", au);
            if(authorCheckList != null) {

                checkList = mdl.andSearch(checkList, authorCheckList);
            }
        }
        if(!pu.equals("")) {

            publisherCheckList = mdl.searchBook("publisher", pu);
            if(publisherCheckList != null) {

                checkList = mdl.andSearch(checkList, publisherCheckList);
            }
        }
        if(!is.equals("")) {

            isbnCheckList = mdl.searchBook("isbn", is);
            if(isbnCheckList != null) {

                checkList = mdl.andSearch(checkList, isbnCheckList);
            }
        }

        return checkList;
    }

    public String getBookList(String data) {
        String listData;

        listData = mdl.getBookList(data);
        return listData;
    }

    public String getBookList(String data, ArrayList<Integer> list) {
        String listData;

        listData = mdl.getBookList(data, list);
        return listData;
    }

    public int getBookListNum() {
        int listNum;

        listNum = mdl.getBookListNum();
        return listNum;
    }

    public void writeBookList() {
        mdl.writeBookList();
    }
}
