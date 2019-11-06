import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
// 향수에 대한 간단한 소개를 작성한 클래스
// Only Label
public class infoScent {
    static JFrame frame;
    JPanel panel;
    JScrollPane scroll;
    static JButton backHome;

    // TODO: 2019-11-04 스크롤바를 어떻게 만들어서 넣을건지
    public infoScent(){
        frame = new JFrame("InfoScent");
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 1000, 10000);
        panel.setBackground(new Color(255, 171, 157));
        scroll = new JScrollPane();
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        Dimension size = new Dimension();
        size.setSize(1000, 1800);
        panel.setPreferredSize(size);
        scroll.setViewportView(panel);

        JLabel titleLb = new JLabel("향수 ");
        JLabel subTitleLb = new JLabel("; perfume, scent ");
        titleLb.setFont(Program.nanumMyengjo);
        subTitleLb.setFont   (new Font("NanumMyeongjo", Font.BOLD, 30));
        titleLb.setBounds(200, 190, 200, 50);
        subTitleLb.setBounds(325, 195, 300, 50);

        JLabel infoLb = new JLabel("<html>: 화장품의 하나. 향료를 알코올 따위에 풀어 만든 액체 .<br/><hr style=color:black><br/>" +
                "어원인 라틴어 ‘per fumum’은 ‘연기를 통한다’는 의미를 담고 있는 향수는 인류가 최초로 사용한 화장품이라고 볼 수 있다.</html>");
        infoLb.setFont(new Font("NanumMyeongjo", Font.BOLD, 20));
        infoLb.setBounds(200, 220, 600, 150);

        JLabel kindsLb = new JLabel("<html><b>지속시간에 따른 분류 --------------</b><p/>" +
                "- 퍼퓸(perfume)<br/>" +
                ": 평균 6~7 시간 정도 향이 가장 오래 감<br/>" +
                "- 오드퍼퓸(eau de perfume)<br/>" +
                ": 퍼퓸보다 조금 연해서 5~6 시간 정도 지속<br/>" +
                "- 오드뜨왈렛(eau de toilette)<br/>" +
                ": 3~5시간 지속되며 대중적인 농도임<br/>" +
                "- 오드콜로뉴(eau de cologne)<br/>" +
                ": 가장 연한 향수 종류 축에 속하며 부담없이 편하게 1~2시간 지속" +
                "<p/><br/>" +
                "<b>농도에 따른 분류 ---------------------------</b><br/>" +
                "<small>부향률 : 향수원액과 알콜의 비율, 높을수록 원액 함유량이 높은 것</small><br/>" +
                "- 파르(Parfum): <br/>" +
                "가장 농도가 진한 것. 부향률 15~40%.<br/>" +
                "- 에스프리드파르(Esprit de Parfum, ESdP): <br/>" +
                "오드파르과 파르의 중간. 부향률 15~30%. 하지만 흔히 사용되지 않음.<br/>" +
                "- 오드파르(EdP), 파르드투알레트 (PdT): <br/>" +
                "부향률 10~20%. 1980년대에 많이 쓰였지만 지금은 잘 쓰이지 않는 용어.<br/>" +
                "- 오드투알레트 (Eau de Toilette, EdT) : <br/>" +
                "부향률 5~15%. 일반적으로 10%.<br/>" +
                "- 오드콜로뉴 (Eau de Cologne, EdC): <br/>" +
                "부향률 3~8%. 일반적으로 5%.<br/>" +
                "이외에도 \"스플래쉬\", \"미스트\" \"베일\" 등의 용어를 사용한다.<br/>" +
                "대개 이런 제품들은 3% 이하의 부향률을 가진다.");
        kindsLb.setFont(Program.nanumGodic);
        kindsLb.setBounds(200, 350, 600, 600);

        JLabel scentNote = new JLabel("<html>향수는 생성부터 소멸까지 단계별로 발향이 되는 특징을 지니고 있다. <br/><br/>" +
                "<b>- 탑노트</b><br/>" +
                ": 헤드노트(head note)라고도 불린다. <br/>" +
                "탑노트는 향수를 시향지 또는 피부에 뿌린 후 발향이 될 때 느낄 수 있는 향이다. <br/>" +
                "하지만 탑노트는 일시적인 향으로 30 분 이하의 시간만 지속된다.<br/>" +
                " 보통은 상쾌한 시트러스 계열이나 네츄럴한 느낌의 그린 계열이 많이 사용된다.<br/><br/>" +
                "<b>- 미들노트</b><br/>" +
                ": 미들노트는 향수의 ‘브랜드 아이덴티티’라고 한다. <br/>" +
                "하트노트(heart note), 소울노트(soul note)라고도 불린다.<br/>" +
                "조향사가 의도한 향이 발향되는 단계로 알려져 있다. <br/>" +
                "탑노트가 끝남과 동시에 1 시간 정도 지속되어 누군가를 만나거나 스쳐 지나갈 때 자극하는 향들은 대부분 미들노트라고 생각하면 된다.<ㅠㄱ/>" +
                "스파이시 계열이나 플로랄 계열과 같이 이미지와 컨셉이 명확한 향료들이 많이 사용된다.<br/><br/>" +
                "<b>- 베이스노트</b><br/>" +
                ": 라스트노트(last note), 잔향이라고도 불린다.<br/>" +
                "향수 종류에 따라 남은 시간 동안 은은하게 남는다.<br/>" +
                "따로 세척이나 커버를 하지 않는다면 피부나 옷에 스며들 정도로 보류제 역할을 한다.<br/>" +
                "조향사에 따라 다르긴 하지만 대부분 대중적이고 저자극적이며 성질이 약한 향들이 많이 쓰인다.</html>");
        scentNote.setFont(Program.nanumGodic);
        scentNote.setBounds(200, 900, 600, 750);

        JLabel lasting = new JLabel("<html>*** 보통 향수의 유통기한은 개봉 전 3~5년, 개봉 후 1~3년 사이다. 유통기한이 지난 향수는 향이 변하거나 향수 액이 변질했을 위험이 있으니 인체에 뿌리기보다는 다른 용도로 사용하는 게 좋다.</html>");
        lasting.setFont(Program.nanumGodic);
        lasting.setBounds(200, 1350, 600, 700);

        ImageIcon backImg = new ImageIcon("src/img/house.png");
        Image chImg = backImg.getImage();
        chImg = chImg.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        backImg = new ImageIcon(chImg);
        backHome = new JButton(backImg);
        backHome.setCursor(new Cursor(Cursor.HAND_CURSOR));
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

        panel.add(titleLb);
        panel.add(subTitleLb);
        panel.add(infoLb);
        panel.add(kindsLb);
        panel.add(scentNote);
        panel.add(lasting);
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
}
