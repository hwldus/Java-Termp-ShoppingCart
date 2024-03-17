package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.io.*;
import java.util.Calendar;


class cThread extends Thread {
	JLabel tLabel;
	
	public cThread(JLabel tLabel) {
		this.tLabel = tLabel;
	}
	
	@Override
	public void run() {
		while(true) {
			Calendar c = Calendar.getInstance();
			int hour = c.get(Calendar.HOUR_OF_DAY);
			int min = c.get(Calendar.MINUTE);
			int second = c.get(Calendar.SECOND);
			
			String clockText = Integer.toString(hour);
			clockText = clockText.concat(":");
			
			clockText = clockText.concat(Integer.toString(min));
			clockText = clockText.concat(":");
			
			clockText = clockText.concat(Integer.toString(second));
			
			tLabel.setText(clockText);
		}
	}
}


public class Project extends JFrame {
	public MyPanel panel = new MyPanel();
	JButton btn;
	JCheckBox chk1, chk2, chk3, chk4, chk5, chk6;
	Clip clip = null;
	AudioInputStream audioStream = null;
	JLabel msgLabel = new JLabel();
	JLabel tLabel = new JLabel();

	public Project() {
		setTitle("떡볶이 장바구니");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);
		panel.setLayout(null);

		
		msgLabel.setBounds(300, 15, 300, 30);
		msgLabel.setFont(new Font("TimesRoman", Font.BOLD, 25));
		panel.add(msgLabel);
		
		JLabel label =new JLabel("현재시간:");
		label.setBounds(700, 0, 150, 80);
		label.setFont(new Font("TimesRoman", Font.BOLD, 25));		
		panel.add(label);
		
		tLabel.setBounds(820, 0, 100, 80);
		tLabel.setFont(new Font("TimesRoman", Font.BOLD, 25));
		panel.add(tLabel);
		
		cThread th = new cThread(tLabel);
		th.start();
		
		setSize(1000, 850);
		setVisible(true);
		
		playAudio("audio/떡볶이송.wav");
		msgLabel.setText("떡볶이송 연주 중");
		
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
				msgLabel.setText("떡볶이송 연주 계속");
				clip.start(); // 중단된 위치에서부터 시작				
			}
			
		 	@Override
			public void mouseExited(MouseEvent e) {
				msgLabel.setText("떡볶이송 연주 일시 중단");
				clip.stop(); // 중단된 위치에서부터 시작				
			}

		});
		
	}
	
	void playAudio(String pathName) {
		try {
			File audioFile = new File(pathName); // 오디오 파일의 경로명
			audioStream = AudioSystem.getAudioInputStream(audioFile); // 오디오 파일로부터
			
			clip = AudioSystem.getClip(); // 비어있는 오디오 클립 만들기
			clip.open(audioStream); // 재생할 오디오 스트림 열기
			clip.start(); // 재생 시작
		}
		catch (LineUnavailableException e) { e.printStackTrace(); }
		catch (UnsupportedAudioFileException e) { e.printStackTrace(); }
		catch (IOException e) { e.printStackTrace(); }
	}
	
	
	

	
	class MyPanel extends JPanel implements ActionListener {
		ImageIcon image1 = new ImageIcon("images/1국물떡볶이.png");
		Image img1 = image1.getImage();
		ImageIcon image2 = new ImageIcon("images/2라볶이.png");
		Image img2 = image2.getImage();
		ImageIcon image3 = new ImageIcon("images/3치즈떡볶이.png");
		Image img3 = image3.getImage();
		ImageIcon image4 = new ImageIcon("images/4로제떡볶이.png");
		Image img4 = image4.getImage();
		ImageIcon image5 = new ImageIcon("images/5크림떡볶이.png");
		Image img5 = image5.getImage();
		ImageIcon image6 = new ImageIcon("images/6차돌떡볶이.png");
		Image img6 = image6.getImage();
		
		JButton btn = new JButton("주문");
		
		JCheckBox chk1 = new JCheckBox("국물떡볶이 5,000원", false);		
		JCheckBox chk2 = new JCheckBox("라볶이 5,500원", false);		
		JCheckBox chk3 = new JCheckBox("치즈떡볶이 5,500원", false);		
		JCheckBox chk4 = new JCheckBox("로제떡볶이 6,000원", false);		
		JCheckBox chk5 = new JCheckBox("크림떡볶이 6,000원", false);		
		JCheckBox chk6 = new JCheckBox("차돌떡볶이 7,000원", false);

		
		public MyPanel() {
			btn.setBounds(400,750,100,30);
			btn.addActionListener(this);
			add(btn);
			
			chk1.setBounds(250, 130, 150, 50);
			add(chk1);
			chk2.setBounds(720, 130, 150, 50);
			add(chk2);
			chk3.setBounds(250, 370, 150, 50);
			add(chk3);
			chk4.setBounds(720, 370, 150, 50);
			add(chk4);
			chk5.setBounds(250, 610, 150, 50);
			add(chk5);
			chk6.setBounds(720, 610, 150, 50);
			add(chk6);
			
				
		}
		
		public void actionPerformed(ActionEvent e) {
			int sum = 0;
			String order = "";
			if(chk1.isSelected()) {
				sum += 5000;
				order += "국물떡볶이 : 5,000원\n";
			}
			if(chk2.isSelected()) {
				sum += 5500;
				order += "라볶이 : 5,500원\n";
			}
			if(chk3.isSelected()) {
				sum += 5500;
				order += "치즈떡볶이 : 5,500원\n";
			}
			if(chk4.isSelected()) {
				sum += 6000;
				order += "로제떡볶이 : 6,000원\n";
			}
			if(chk5.isSelected()) {
				sum += 6000;
				order += "크림떡볶이 : 6,000원\n";
			}
			if(chk6.isSelected()) {
				sum += 7000;
				order += "차돌떡볶이 : 7,000원\n";
			}
			order += "-------------------------\n";
			JOptionPane.showMessageDialog(null, order + "총 합계: "+ sum, "주문서", JOptionPane.INFORMATION_MESSAGE);
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img1, 30, 60, 180, 180, this);
			g.drawImage(img2, 500, 60, 180, 180, this);
			g.drawImage(img3, 30, 300, 180, 180, this);
			g.drawImage(img4, 500, 300, 180, 180, this);
			g.drawImage(img5, 30, 550, 180, 180, this);
			g.drawImage(img6, 500, 550, 180, 180, this);
		}
	}

	public static void main(String[] args) {
		new Project();
	
	}

}