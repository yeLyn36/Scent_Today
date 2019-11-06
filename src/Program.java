//" 오늘 당신의 향수는 무엇인가요 ? " (Scent, today) 사용 설명서

//1. Program.java 실행해주세요
//2. 원하는 버튼을 클릭해주세요
//(1) '향수란, ' 버튼을 클릭했다면 향수에 대한 간단한 소개를 해놓았으니 꼭 다 읽어보세요. 길다고 무시하면 슬퍼요 ;(
//(2) '오늘의 향수' 버튼을 클릭했다면 간단한 6가지 설문에 답해주세요 !! 하나라도 답하지 않는다면 넘어가지 않으니 꼭꼭 모두 답해주세요 :)
//     모두 답하셨다면 '결과' 버튼을 눌러주세요 ! 오늘의 당신에게 어울리는 향수의 향과 지속시간에 따른 양수를 추천해드려요 !
//     관심있는 향수가 있다면 옆에 저장하기 버튼을 눌러주세요 !! 나중에 당신의 향수 리스트에서 찾아볼 수 있어요
//(3) '향수 리스트' 버튼을 클릭했다면 지금까지 당신이 저장했던 향수들의 목록을 보실 수 있어요 !
//     테이블 상단의 '.txt로 보기' 버튼을 클릭하신다면 위의 목록을 메모장 파일 (list.txt)로 만나보실 수 있아요
//3. 다시 홈으로 돌아가고 싶으시다면 각 창의 위치한 집 모양의 버튼을 클릭해주세요 ! 첫 화면으로 돌아오실 수 있어요 :)
//" 오늘 당신의 향수는 무엇인가요? " (Scent, today)를 즐겁게 체험하셨나요 ?
//더 많은 데이터와 향으로 업데이트되어 돌아오겠습니당 !
//오늘도 향긋한 하루 되세요 !


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// TODO: 2019-11-01 구글 폰트 정하기
// English title : Lora-BoldItalic
// Korean title : 나눔명조
// Korean text : 나눔바른고딕
// English text : merriweather sans
public class Program {
    static Font lora = new Font("Lora", Font.BOLD, 50);
    static Font nanumGodic = new Font("나눔바른고딕", Font.PLAIN, 20);
    static Font merriweather = new Font("Merriweather", Font.PLAIN, 20);
    static Font nanumMyengjo = new Font("NanumMyeongjo", Font.BOLD, 50);

    static JFrame frame;
    JPanel panel;
    ImageIcon infoImg;
    static JButton infoBtn;
    ImageIcon surImg;
    static JButton surBtn;
    ImageIcon listImg;
    static JButton listBtn;

    public Program(){
        frame = new JFrame("SCENToday");
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(255, 171, 157));

        JLabel titleLb = new JLabel("SCENT, today");
        titleLb.setBounds(330, 110, 330, 70);
        titleLb.setFont(lora);

        ImageIcon scentImg = new ImageIcon("src/img/perfume.png");
        Image titleImg = scentImg.getImage();
        titleImg = titleImg.getScaledInstance(350, 350, Image.SCALE_SMOOTH);
        scentImg = new ImageIcon(titleImg);
        JLabel imgIcon = new JLabel(scentImg);
        imgIcon.setBounds(325, 190, 400, 400);

        infoImg = new ImageIcon("src/img/infoBtn.png");
        infoBtn = new JButton(infoImg);
        infoBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        infoBtn.setOpaque(false);
        infoBtn.setContentAreaFilled(false);
        infoBtn.setBorderPainted(false);
        infoBtn.setBounds(100, 600, 210, 80);
        infoBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                showInfo();
            }
        });

        surImg = new ImageIcon("src/img/todayBtn.png");
        surBtn = new JButton(surImg);
        surBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        surBtn.setOpaque(false);
        surBtn.setContentAreaFilled(false);
        surBtn.setBorderPainted(false);
        surBtn.setBounds(400, 600, 210, 80);
        surBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                startSurvey();
            }
        });

        listImg = new ImageIcon("src/img/listBtn.png");
        listBtn = new JButton(listImg);
        listBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        listBtn.setOpaque(false);
        listBtn.setContentAreaFilled(false);
        listBtn.setBorderPainted(false);
        listBtn.setBounds(700, 600, 210, 80);
        listBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                showWantedList();
            }
        });

        panel.add(titleLb);
        panel.add(imgIcon);
        panel.add(infoBtn);
        panel.add(surBtn);
        panel.add(listBtn);
        frame.add(panel);

        frame.setPreferredSize(new Dimension(1000, 800));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((screenSize.width - 1000) /2, (screenSize.height - 800) /2);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void showInfo(){
        new infoScent();
        frame.dispose();
    }

    public void startSurvey(){
        new SurveyScent();
        frame.dispose();
    }

    public void showWantedList(){
        new WantedScent();
        frame.dispose();
    }

    public static void main(String[] args) {
        new Program();
    }
}