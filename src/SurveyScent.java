import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SurveyScent {
    static JFrame frame;
    JPanel panel;
    static JButton backHome;
    JRadioButton[] mood = {new JRadioButton("나른한 행복함"), new JRadioButton("날아갈 거 같이 신남"),
            new JRadioButton("몸이 무겁고 피곤함"), new JRadioButton("슬픈 영화를 보고 울고 싶을 만큼 슬픔"),
            new JRadioButton("아무것도 하기 싫음"), new JRadioButton("다 찢어버리고 싶을 정도로 화가 남"),
            new JRadioButton("모든 게 눈에 거슬리고 까칠해짐")};
    JRadioButton[] person = {new JRadioButton("애인"), new JRadioButton("친한 친구나 언니 오빠"),
            new JRadioButton("선후배"), new JRadioButton("가족"),
            new JRadioButton("혼자"), new JRadioButton("기타")};
    JRadioButton[] todo = {new JRadioButton("영화보기"), new JRadioButton("맛있는 음식 먹기"),
            new JRadioButton("놀러가기(쇼핑, 여행)"), new JRadioButton("공부하기"),
            new JRadioButton("휴식"), new JRadioButton("아무것도 안 함")};
    JRadioButton[] fassion = {new JRadioButton("캐주얼"), new JRadioButton("러블리"),
            new JRadioButton("오피스룩"), new JRadioButton("럭셔리"), new JRadioButton("빈티지"),
            new JRadioButton("섹시"), new JRadioButton("심플베이직"), new JRadioButton("스쿨룩")};
    JRadioButton[] wantMood = {new JRadioButton("차분하고 조용한 분위기"), new JRadioButton("통통 튀고 밝은 분위기"),
            new JRadioButton("시원하고 청량한 분위기"), new JRadioButton("우아하고 그윽한 분위기")};
    JRadioButton often[] = {new JRadioButton("하루에 1번"), new JRadioButton("하루에 3번 이상"),
            new JRadioButton("하루에 5번 이상"), new JRadioButton("일주일에 1번"),
            new JRadioButton("잘 안 뿌림")};

    public SurveyScent(){
        frame = new JFrame("InfoScent");
        panel = new JPanel();
        JScrollPane scroll = new JScrollPane();
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        Dimension size = new Dimension();
        size.setSize(1000, 1700);
        panel.setPreferredSize(size);
        scroll.setViewportView(panel);
        panel.setLayout(null);
        panel.setBounds(100, 100, 10000, 10000);
        panel.setBackground(new Color(255, 171, 157));
        JLabel titleLb = new JLabel("오늘의 향수는 무엇인가요 ?");
        titleLb.setFont(Program.nanumMyengjo);
        titleLb.setBounds(200, 150, 900, 70);
        JLabel helpLb = new JLabel("아래 6개의 설문을 마친 후 결과 버튼을 눌러주세요 :)");
        helpLb.setFont(Program.nanumGodic);
        helpLb.setBounds(210, 200, 900, 70);

        makeMoodPn();
        makePersonPn();
        makeTodoPn();
        makeFassionPn();
        makeWantMoodPn();
        makeOftenPn();

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

        ImageIcon resultImg = new ImageIcon("src/img/resultBtn.png");
        JButton resultBtn = new JButton(resultImg);
        resultBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        resultBtn.setOpaque(false);
        resultBtn.setContentAreaFilled(false);
        resultBtn.setBorderPainted(false);
        resultBtn.setBounds(350, 1500, 300, 100);
        resultBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String[] selected = {getResult(mood), getResult(person), getResult(todo), getResult(fassion), getResult(wantMood), getResult(often)};
                boolean check = true;
                for (int i = 0; i < selected.length; i++){
                    if(selected[i] == null){
                        check = false;
                        break;
                    }
                }
                if (check) {
                    initSelected(mood);
                    initSelected(person);
                    initSelected(todo);
                    initSelected(fassion);
                    initSelected(wantMood);
                    initSelected(often);
                    new Result(selected);
                    frame.dispose();
                } else
                    JOptionPane.showMessageDialog(null, "입력되지 않은 값이 있습니다.");
            }
        });

        panel.add(titleLb);
        panel.add(helpLb);
        panel.add(resultBtn);
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

    public void makeMoodPn(){
        JPanel moodPn = new JPanel();
        moodPn.setLayout(new FlowLayout());
        moodPn.setOpaque(false);
        moodPn.setBounds(100, 430, 800, 200);
        // TODO: 2019-11-04 radio 버튼 걸고 값 넘기는 것까지 하기
        JLabel quest1 = new JLabel("Q1. 오늘 당신의 기분은 어떤가요 ?");
        quest1.setFont(new Font("NanumMyeongjo", Font.BOLD, 35));
        quest1.setBounds(240, 370, 700, 50);
        ButtonGroup moodGroup = new ButtonGroup();
        for(int i = 0; i < mood.length; i++){
            mood[i].setFont(Program.nanumGodic);
            mood[i].setOpaque(false);
            moodGroup.add(mood[i]);
            moodPn.add(mood[i]);
        }
        panel.add(quest1);
        panel.add(moodPn);
    }
    public void makePersonPn(){
        JPanel personPn = new JPanel();
        personPn.setLayout(new FlowLayout());
        personPn.setOpaque(false);
        personPn.setBounds(100, 660, 800, 200);
        JLabel quest2 = new JLabel("Q2. 오늘 누구와 있을 예정인가요 ?");
        quest2.setFont(new Font("NanumMyeongjo", Font.BOLD, 35));
        quest2.setBounds(240, 600, 700, 50);

        ButtonGroup personGroup = new ButtonGroup();
        for(int i = 0; i < person.length; i++){
            person[i].setFont(Program.nanumGodic);
            person[i].setOpaque(false);
            personGroup.add(person[i]);
            personPn.add(person[i]);
        }
        panel.add(quest2);
        panel.add(personPn);
    }
    public void makeTodoPn(){
        JPanel todoPn = new JPanel();
        todoPn.setLayout(new FlowLayout());
        todoPn.setOpaque(false);
        todoPn.setBounds(100, 840, 800, 200);
        // TODO: 2019-11-04 radio 버튼 걸고 값 넘기는 것까지 하기
        JLabel quest3 = new JLabel("Q3. 오늘 무엇을 할 예정인가요 ?");
        //1. 영화보기 2. 맛있는 음식 먹기 3. 놀러가기(쇼핑, 여행) 4. 공부하기 6. 휴식 5. 아무것도 안 함
        quest3.setFont(new Font("NanumMyeongjo", Font.BOLD, 35));
        quest3.setBounds(240, 780, 700, 50);
        ButtonGroup todoGroup = new ButtonGroup();
        for(int i = 0; i < todo.length; i++){
            todo[i].setFont(Program.nanumGodic);
            todo[i].setOpaque(false);
            todoGroup.add(todo[i]);
            todoPn.add(todo[i]);
        }
        panel.add(quest3);
        panel.add(todoPn);
    }
    public void makeFassionPn(){
        JPanel fassionPn = new JPanel();
        fassionPn.setLayout(new FlowLayout());
        fassionPn.setOpaque(false);
        fassionPn.setBounds(100, 1000, 800, 200);
        // TODO: 2019-11-04 radio 버튼 걸고 값 넘기는 것까지 하기
        JLabel quest4 = new JLabel("Q4. 오늘 당신의 패션은 어느 스타일인가요 ?");
        quest4.setFont(new Font("NanumMyeongjo", Font.BOLD, 35));
        quest4.setBounds(180, 940, 700, 50);
        ButtonGroup fassionGroup = new ButtonGroup();
        for(int i = 0; i < fassion.length; i++){
            fassion[i].setFont(Program.nanumGodic);
            fassion[i].setOpaque(false);
            fassionGroup.add(fassion[i]);
            fassionPn.add(fassion[i]);
        }
        panel.add(quest4);
        panel.add(fassionPn);
    }
    public void makeWantMoodPn(){
        JPanel wantMoodPn = new JPanel();
        wantMoodPn.setLayout(new FlowLayout());
        wantMoodPn.setOpaque(false);
        wantMoodPn.setBounds(100, 1150, 800, 200);
        JLabel quest5 = new JLabel("Q5. 오늘 당신은 어떤 분위기를 원하나요 ?");
        //1. 차분하고 조용한 분위기 2. 통통 튀고 밝은 분위기 3. 시원하고 청량한 분위기 4. 우아하고 그윽한 분위기
        quest5.setFont(new Font("NanumMyeongjo", Font.BOLD, 35));
        quest5.setBounds(180, 1100, 700, 50);
        ButtonGroup wantMoodGroup = new ButtonGroup();
        for(int i = 0; i < wantMood.length; i++){
            wantMood[i].setFont(Program.nanumGodic);
            wantMood[i].setOpaque(false);
            wantMoodGroup.add(wantMood[i]);
            wantMoodPn.add(wantMood[i]);
        }
        panel.add(quest5);
        panel.add(wantMoodPn);
    }
    public void makeOftenPn(){
        JPanel oftenPn = new JPanel();
        oftenPn.setLayout(new FlowLayout());
        oftenPn.setOpaque(false);
        oftenPn.setBounds(100, 1330, 800, 200);
        JLabel quest6 = new JLabel("Q6. 당신은 향수를 얼마나 자주 뿌리나요 ?");
        //1. 하루에 1번 2. 하루에 3번 이상 //하루에 5번 이상 //일주일에 1번 //잘 안 뿌림
        quest6.setFont(new Font("NanumMyeongjo", Font.BOLD, 35));
        quest6.setBounds(180, 1280, 700, 50);
        ButtonGroup oftenGroup = new ButtonGroup();
        for(int i = 0; i < often.length; i++){
            often[i].setFont(Program.nanumGodic);
            often[i].setOpaque(false);
            oftenGroup.add(often[i]);
            oftenPn.add(often[i]);
        }
        panel.add(quest6);
        panel.add(oftenPn);
    }
    public String getResult(JRadioButton[] radios){
        for(int i = 0; i < radios.length; i++){
            if (radios[i].isSelected()){
                return radios[i].getText();
            }
        }
        return null;
    }
    public void initSelected(JRadioButton[] radios){
        for(int i = 0; i < radios.length; i++){
            if(radios[i].isSelected()) {
                radios[i].setSelected(false);
            }
        }
    }

    public static void main(String[] args) {
        new SurveyScent();
    }
}
