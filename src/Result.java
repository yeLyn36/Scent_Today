import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.*;
import java.util.ArrayList;

// TODO: 2019-11-04 넘어온 값으로 정보 찾고 리스트 가져오기 
// TODO: 2019-11-04 리스트로 사이트 넘어가고 파일에 추가 
public class Result {
    static JFrame frame;
    JPanel panel;
    static JTable table;
    JScrollPane scroll;
    static JButton backHome;
    static Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = null;
    String scents;
    String kind;

    public Result(String[] selected) {
        connectDB();
        table = checkScent(selected);
        table.setPreferredScrollableViewportSize(new Dimension(1000, 500));
        table.setAutoscrolls(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setRowHeight(100);
        table.setShowGrid(true);
        table.setBounds(50, 500, 900, table.getRowCount()*100);
        table.getColumnModel().getColumn(5).setCellRenderer(new TableCell());;
        table.getColumnModel().getColumn(5).setCellEditor(new TableCell());;

        frame = new JFrame("Result");
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(100, 100, 10000, 2500);
        panel.setBackground(new Color(255, 171, 157));
        scroll = new JScrollPane();
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        Dimension size = new Dimension();
        size.setSize(1000, table.getRowCount()*200);
        panel.setPreferredSize(size);
        scroll.setViewportView(panel);

        JLabel titleLb = new JLabel("<html>오늘 당신의 향은 <b>" + scents + "</b>향입니다.");
        titleLb.setFont(Program.nanumMyengjo);
        titleLb.setBounds(100, 150, 1000, 200);
        JLabel subtitleLb = new JLabel("<html>당신에겐 " +
                "<b>" + kind + "</b>를 추천합니다.</html>");
        subtitleLb.setFont(new Font("NanumMyeongjo", Font.BOLD, 30));
        subtitleLb.setBounds(100, 200, 1000, 200);
        JLabel helpLb = new JLabel("아래 향수 목록 옆 버튼을 누르면 당신의 향수 리스트에 항목을 추가할 수 있어요 !");
        helpLb.setFont(Program.nanumGodic);
        helpLb.setBounds(110, 230, 1000, 200);

        ImageIcon backImg = new ImageIcon("src/img/house.png");
        Image chImg = backImg.getImage();
        chImg = chImg.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        backImg = new ImageIcon(chImg);
        backHome = new JButton(backImg);
        backHome.setOpaque(false);
        backHome.setContentAreaFilled(false);
        backHome.setBorderPainted(false);
        backHome.setBounds(850, 40, 100, 100);
        backHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.dispose();
                new Program();
            }
        });

        panel.add(table);
        panel.add(titleLb);
        panel.add(subtitleLb);
        panel.add(helpLb);
        panel.add(backHome);
        frame.add(scroll);
        frame.setPreferredSize(new Dimension(1000, 800));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((screenSize.width - 1000) / 2, (screenSize.height - 800) / 2);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private JTable checkScent(String[] selected) {

        String[] scentName = {"플로랄", "프루티", "애니멀릭", "아쿠아", "파우더리", "구르망", "우디"};
        int scent[] = {0, 0, 0, 0, 0, 0, 0};
        // 플로랄, 프루티, 애니멀릭, 아쿠아, 파우더리, 구르망, 우디
        String[] kindsName = {"퍼퓸", "오드퍼퓸", "오드뜨왈렛", "오드콜로뉴", "그외"};
        int[] kinds = {0, 0, 0, 0, 0};
        //퍼퓸, 오드퍼퓸, 오드뜨왈렛, 오드콜로뉴, 그외

        String mood = selected[0];
        switch (mood) {
            case "나른한 행복함":
                scent[0]++;
                break;
            case "날아갈 거 같이 신남":
                scent[1]++;
                break;
            case "몸이 무겁고 피곤함":
                scent[2]++;
                break;
            case "슬픈 영화를 보고 울고 싶을 만큼 슬픔":
                scent[6]++;
                break;
            case "아무것도 하기 싫음":
                scent[4]++;
                break;
            case "다 찢어버리고 싶을 정도로 화가 남":
                scent[3]++;
                break;
            case "모든 게 눈에 거슬리고 까칠해짐":
                scent[5]++;
                break;
        }
        String person = selected[1];
        switch (person) {
            case "애인":
                scent[0]++;
                scent[1]++;
                break;
            case "친한 친구나 언니 오빠":
                scent[5]++;
                break;
            case "선후배":
                scent[4]++;
                break;
            case "가족":
                scent[6]++;
                break;
            case "혼자":
                scent[2]++;
                break;
            case "기타":
                scent[3]++;
                break;
        }
        String todo = selected[2];
        switch (todo) {
            case "영화보기":
                scent[2]++;
                break;
            case "맛있는 음식 먹기":
                scent[4]++;
                scent[1]++;
                break;
            case "놀러가기(쇼핑, 여행)":
                scent[0]++;
                break;
            case "공부하기":
                scent[3]++;
                break;
            case "휴식":
                scent[5]++;
                break;
            case "아무것도 안 함":
                scent[6]++;
                break;
        }
        String fassion = selected[3];
        switch (fassion) {
            case "캐주얼":
                scent[1]++;
                scent[3]++;
                break;
            case "러블리":
                scent[0]++;
                scent[1]++;
                break;
            case "오피스룩":
                scent[1]++;
                scent[6]++;
                break;
            case "럭셔리":
                scent[2]++;
                scent[6]++;
                break;
            case "빈티지":
                scent[4]++;
                scent[3]++;
                break;
            case "섹시":
                scent[2]++;
                scent[5]++;
                break;
            case "심플베이직":
                scent[4]++;
                scent[5]++;
                break;
            case "스쿨룩":
                scent[4]++;
                scent[1]++;
                break;
        }
        String wantMood = selected[4];
        switch (wantMood) {
            case "차분하고 조용한 분위기":
                scent[2]++;
                scent[4]++;
                break;
            case "통통 튀고 밝은 분위기":
                scent[0]++;
                scent[1]++;
                break;
            case "시원하고 청량한 분위기":
                scent[3]++;
                break;
            case "우아하고 그윽한 분위기":
                scent[5]++;
                scent[6]++;
                break;
        }
        String often = selected[5];
        switch (often) {
            case "하루에 1번":
                kinds[0]++;
                break;
            case "하루에 3번 이상":
                kinds[1]++;
                break;
            case "하루에 5번 이상":
                kinds[2]++;
                break;
            case "일주일에 1번":
                kinds[3]++;
                break;
            case "잘 안 뿌림":
                kinds[4]++;
                break;
        }

        int max = scent[0];
        scents = scentName[0];
        for (int i = 1; i < scent.length; i++) {
            if (max < scent[i]) {
                max = scent[i];
                scents = scentName[i];
            }
        }
        max = kinds[0];
        kind = kindsName[0];
        for (int i = 0; i < kinds.length; i++) {
            if (max < kinds[i]) {
                max = kinds[i];
                kind = kindsName[i];
            }
        }

        JTable table = selectList(scents, kind);
        return table;
    }

    public void connectDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:scents.db");
            sql = "CREATE TABLE IF NOT EXISTS tblScent (" +
                    "name TEXT NOT NULL PRIMARY KEY, " +
                    "scent TEXT, " +
                    "kinds TEXT, " +
                    "info TEXT, " +
                    "site TEXT );";
            pstmt = conn.prepareStatement(sql);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("드라이버 로딩 오류");
        }
    }

    public void saveList(String name, String scent, String kinds, String info, String site) {
        String row = name + "\t" + scent + "\t" + kinds + "\t" + info + "\t" + site + "\n";
        try {
            FileWriter fw = new FileWriter("src/list.txt", true);
            fw.write(row);
            fw.flush();
            fw.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public JTable selectList(String scents, String kind) {
        JTable table;
        sql = "select * from tblScent where scent = \"" + scents + "\"";
        String[] colsName = {"이름", "향기", "지속시간", "정보", "주소", "저장"};
        Object[][] data = new Object[100][6];
        int i = 0;
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    data[i][0] = rs.getString("name");
                    data[i][1] = rs.getString("scent");
                    data[i][2] = rs.getString("kinds");
                    data[i][3] = rs.getString("info");
                    data[i][4] = rs.getString("site");
                    System.out.println(data[i][4]);
                    i++;
                }
            }
            Object[][] rowData =  new Object[i][6];
            for (int j = 0; j < i; j++){
                rowData[j] = data[j];
            }
            table = new JTable(rowData, colsName);
            return table;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("데이터 로딩 및 테이블 생성 오류");
        }
        return null;
    }

    class TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {

        JButton jb;

        public TableCell() {
            ImageIcon btnImg = new ImageIcon("src/img/addList.png");
            jb = new JButton(btnImg);

            jb.addActionListener(e -> {
                JOptionPane.showMessageDialog(null, "당신의 향수 리스트에 추가되었습니다.");
                String name = String.valueOf(table.getValueAt(table.getSelectedRow(), 0));
                String scent = String.valueOf(table.getValueAt(table.getSelectedRow(), 1));
                String kind = String.valueOf(table.getValueAt(table.getSelectedRow(), 2));
                String info = String.valueOf(table.getValueAt(table.getSelectedRow(), 3));
                String site = String.valueOf(table.getValueAt(table.getSelectedRow(), 4));
                saveList(name, scent, kind, info, site);
            });

        }

        @Override
        public Object getCellEditorValue() {
            // TODO Auto-generated method stub
            return null;
        }
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            // TODO Auto-generated method stub
            return jb;
        }
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                                                     int column) {
            // TODO Auto-generated method stub
            return jb;
        }

    }

}

