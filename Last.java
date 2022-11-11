import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;


class move extends Thread{
	private JLabel balloon;
	private JPanel c;
	int x, y;
	
	public move(JPanel c, JLabel balloon, int x, int y) {
		this.balloon = balloon;
		this.c = c;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void run() {

		
		while(true) {

			
			try {
				c.add(balloon);

				int setX = (int)(Math.random() * (x - 100) + 50);
				int setY = (int)(Math.random() * (y - 100) + 50);
				balloon.setLocation(setX, setY);
				sleep(1000);
				
			}
			catch(InterruptedException e){
				c.remove(balloon);
				
			}
		}
		
	}
}


public class Last extends JFrame{
	JLabel balloon1 = new JLabel();
	ImageIcon images = new ImageIcon("images/balloon.jpg");
	
	
	// 단어 테스트 받는 static
	static String [] word = new String[6];
	static String [] mean = new String[6];
	
	static int n = 0;
	JLabel scoring = new JLabel(Integer.toString(n));
	
	JLabel question = new JLabel(word[0]);
	JTextField answer = new JTextField(20);

	JLabel check = new JLabel("...");

	public Last() {
		setTitle("R1206");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		
		c.setLayout(new BorderLayout());
		
		
		balloon1.setIcon(images);
		balloon1.setSize(50,50);
		
		
		// 점수 측정
		JPanel panel3 = new JPanel();
				
		panel3.setLayout(new GridLayout(1,2,5,5));
		panel3.setSize(new Dimension(700,100));
				
				
		JLabel score = new JLabel("점수 :");
				
		panel3.add(score);
		panel3.add(scoring);
				
				
				
		//단어 테스트
		
		JPanel panel1 = new JPanel();
		
		panel1.setLayout(new GridLayout(3,2,5,5));
		panel1.setSize(new Dimension(700, 300));
		JLabel eng = new JLabel(" 영어");
		JLabel kor = new JLabel(" 한글");
		
		
		panel1.add(eng);
		panel1.add(kor);
		

		panel1.add(question);
		panel1.add(answer);
		
		JButton next = new JButton("다음");
		

		panel1.add(next);
		panel1.add(check);
		
		next.addActionListener(new ActionListener() {
			int i = 0;

			public void actionPerformed(ActionEvent e) {
				if(answer.getText().equals(mean[i])) {
					n++;
					
					check.setText(word[i] + "은(는) 정답입니다.");

					i++;
					if(i == 6) { i = 0; }
					
					scoring.setText(Integer.toString(n));
					question.setText(word[i]);
						
				}
				else check.setText("오답입니다");
					
			}
		});
		
//-------------------------------------------------------------------
		
	
		
		// 풍선 터뜨리기
		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setSize(new Dimension(700,400));
		
		
		move moving1 = new move(panel2, balloon1, panel2.getWidth(), panel2.getHeight());
		moving1.start();
		
		balloon1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				n++;
				
				scoring.setText(Integer.toString(n));
				moving1.interrupt();
			}
		});
		
		
		
		c.add(panel1, BorderLayout.NORTH);
		c.add(panel2, BorderLayout.CENTER);
		c.add(panel3, BorderLayout.SOUTH);
		

		
		setSize(700, 600);
		setVisible(true);

		
	
			
	}
	public static void main(String[] args) {
		

		try {
		InputStreamReader in = null;
		FileInputStream fin = null;
		File file = new File("C:\\java2021\\workspace2021\\R1206-김순학-단어테스트, 풍선터뜨리기\\Kor.txt");
		File file2 = new File("C:\\java2021\\workspace2021\\R1206-김순학-단어테스트, 풍선터뜨리기\\Eng.txt");
	
		
		fin = new FileInputStream(file);
		in = new InputStreamReader(fin, "UTF-8");
		
		Scanner sc1 = new Scanner(file2);
		Scanner sc2 = new Scanner(in);
		
		for(int i = 0; sc1.hasNextLine(); i++) {
			word[i] = sc1.nextLine();
			System.out.println(word[i]);
		}
		
		for(int i = 0; sc2.hasNextLine(); i++) {
			mean[i] = sc2.nextLine();
			System.out.println(mean[i]);
			}
		
		
		sc1.close();
		sc2.close();
		}
		catch (IOException e) {
			System.out.println("입출력 오류");
		}
		
	
		
		new Last();

	}

}
