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
		setTitle("������ ��ٱ���");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);
		panel.setLayout(null);

		
		msgLabel.setBounds(300, 15, 300, 30);
		msgLabel.setFont(new Font("TimesRoman", Font.BOLD, 25));
		panel.add(msgLabel);
		
		JLabel label =new JLabel("����ð�:");
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
		
		playAudio("audio/�����̼�.wav");
		msgLabel.setText("�����̼� ���� ��");
		
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
				msgLabel.setText("�����̼� ���� ���");
				clip.start(); // �ߴܵ� ��ġ�������� ����				
			}
			
		 	@Override
			public void mouseExited(MouseEvent e) {
				msgLabel.setText("�����̼� ���� �Ͻ� �ߴ�");
				clip.stop(); // �ߴܵ� ��ġ�������� ����				
			}

		});
		
	}
	
	void playAudio(String pathName) {
		try {
			File audioFile = new File(pathName); // ����� ������ ��θ�
			audioStream = AudioSystem.getAudioInputStream(audioFile); // ����� ���Ϸκ���
			
			clip = AudioSystem.getClip(); // ����ִ� ����� Ŭ�� �����
			clip.open(audioStream); // ����� ����� ��Ʈ�� ����
			clip.start(); // ��� ����
		}
		catch (LineUnavailableException e) { e.printStackTrace(); }
		catch (UnsupportedAudioFileException e) { e.printStackTrace(); }
		catch (IOException e) { e.printStackTrace(); }
	}
	
	
	

	
	class MyPanel extends JPanel implements ActionListener {
		ImageIcon image1 = new ImageIcon("images/1����������.png");
		Image img1 = image1.getImage();
		ImageIcon image2 = new ImageIcon("images/2����.png");
		Image img2 = image2.getImage();
		ImageIcon image3 = new ImageIcon("images/3ġ�����.png");
		Image img3 = image3.getImage();
		ImageIcon image4 = new ImageIcon("images/4����������.png");
		Image img4 = image4.getImage();
		ImageIcon image5 = new ImageIcon("images/5ũ��������.png");
		Image img5 = image5.getImage();
		ImageIcon image6 = new ImageIcon("images/6����������.png");
		Image img6 = image6.getImage();
		
		JButton btn = new JButton("�ֹ�");
		
		JCheckBox chk1 = new JCheckBox("���������� 5,000��", false);		
		JCheckBox chk2 = new JCheckBox("���� 5,500��", false);		
		JCheckBox chk3 = new JCheckBox("ġ����� 5,500��", false);		
		JCheckBox chk4 = new JCheckBox("���������� 6,000��", false);		
		JCheckBox chk5 = new JCheckBox("ũ�������� 6,000��", false);		
		JCheckBox chk6 = new JCheckBox("���������� 7,000��", false);

		
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
				order += "���������� : 5,000��\n";
			}
			if(chk2.isSelected()) {
				sum += 5500;
				order += "���� : 5,500��\n";
			}
			if(chk3.isSelected()) {
				sum += 5500;
				order += "ġ����� : 5,500��\n";
			}
			if(chk4.isSelected()) {
				sum += 6000;
				order += "���������� : 6,000��\n";
			}
			if(chk5.isSelected()) {
				sum += 6000;
				order += "ũ�������� : 6,000��\n";
			}
			if(chk6.isSelected()) {
				sum += 7000;
				order += "���������� : 7,000��\n";
			}
			order += "-------------------------\n";
			JOptionPane.showMessageDialog(null, order + "�� �հ�: "+ sum, "�ֹ���", JOptionPane.INFORMATION_MESSAGE);
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