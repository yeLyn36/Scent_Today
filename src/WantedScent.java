import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.StringTokenizer;

public class WantedScent {
    static JFrame frame;
    JPanel panel;
    static JButton backHome;
    JTable table;
    JScrollPane scroll;

    // TODO: 2019-11-04 좋아하는 향수 목록 가져오기
    public WantedScent(){
        table = selectList();
        table.setPreferredScrollableViewportSize(new Dimension(1000, 500));
        table.setAutoscrolls(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setRowHeight(100);
        table.setShowGrid(true);
        table.setBounds(50, 400, 900, table.getRowCount()*100);

        frame = new JFrame("InfoScent");
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(100, 100, 10000, 10000);
        panel.setBackground(new Color(255, 171, 157));
        scroll = new JScrollPane();
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        Dimension size = new Dimension();
        size.setSize(1000, table.getRowCount()*200);
        panel.setPreferredSize(size);
        scroll.setViewportView(panel);

        JLabel titleLb = new JLabel("당신이 좋아하는 향수들의 목록입니다.");
        titleLb.setFont(Program.nanumMyengjo);
        titleLb.setBounds(100, 120, 1000, 200);

        ImageIcon showListImg = new ImageIcon("src/img/showTxt.png");
        JButton ListBtn = new JButton(showListImg);
        ListBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ListBtn.setOpaque(false);
        ListBtn.setContentAreaFilled(false);
        ListBtn.setBorderPainted(false);
        ListBtn.setBounds(650, 280, 300, 100);
        ListBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    showTxt();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "list.txt 파일이 존재하지 않습니다.");
                }
            }
        });

        ImageIcon backImg = new ImageIcon("src/img/house.png");
        Image chImg = backImg.getImage();
        chImg = chImg.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        backImg = new ImageIcon(chImg);
        backHome = new JButton(backImg);
        backHome.setOpaque(false);
        backHome.setContentAreaFilled(false);
        backHome.setBorderPainted(false);
        backHome.setBounds(850, 40, 100, 100);
        backHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Program();
            }
        });

        panel.add(table);
        panel.add(titleLb);
        panel.add(ListBtn);
        panel.add(backHome);
        frame.add(scroll);

        frame.setPreferredSize(new Dimension(1000, 800));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((screenSize.width - 1000) /2, (screenSize.height - 800) /2);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    //향수 리스트 항목을 메모장 파일에서 가져와서 JTable 객체를 이용하여 Table 생성

    public void showTxt() throws IOException {
        Desktop.getDesktop().edit(new File("src/list.txt"));
    } // 메모장 실행해서 파일 열기

    public JTable selectList(){
        FileReader fr;
        try {
            fr = new FileReader("src/list.txt");
            //버퍼 연결 (readLine())
            BufferedReader br = new BufferedReader(fr);
            //내용 읽기
            String csvStr = ""; //모든 내용 연결 용
            String tmpStr = ""; //한줄 읽기 용
            while((tmpStr = br.readLine()) != null) {
                csvStr += tmpStr + "\t";
            }
            // 파일에서 값을 가져와서 모두 합친 후 tap 키를 기준으로 합함.
            StringTokenizer parse = new StringTokenizer(csvStr, "\t");
            int length = parse.countTokens() / 6;
            String[] colsName = {"이름", "향기", "지속시간", "정보", "주소"};
            Object[][] data = new Object[100][5];
            int arrayCount = 0;

            for(int i = 0; i < data.length; i++) {
                for(int j = 0; j < data[i].length; j++){
                    if(parse.hasMoreElements()){
                        data[i][j] = parse.nextToken();
                        arrayCount++;
                    }
                }
            }
            //나눈 값으로 Table 생성할 배열들 생성
            arrayCount = arrayCount/5 + arrayCount%5;
            //항목수 구하기
            Object[][] rowData = new Object[arrayCount][5];
            for(int i = 0; i < arrayCount; i++) {
                for(int j = 0; j < rowData[i].length; j++){
                    rowData[i][j] = data[i][j];
                }
            }
            //테이블 생성 후 반환
            JTable table = new JTable(rowData, colsName);
            return table;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
        //아니면 null 반환, 표시되는 테이블 없음
    }
}
