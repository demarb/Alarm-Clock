package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import clock.Alarm;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtAlarmName;
	JLabel lblCurrentTime = new JLabel("Current Time");
	JTextArea AlarmSoundPathTextArea = new JTextArea();

	private Alarm alarm = new Alarm();
	
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 325, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblCurrentTime.setBounds(0, 25, 310, 37);
		lblCurrentTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentTime.setForeground(new Color(255, 255, 255));
		lblCurrentTime.setFont(new Font("Tahoma", Font.PLAIN, 36));
		contentPane.add(lblCurrentTime);
		
		JLabel lblAlarmSound = new JLabel("Alarm Sound");
		lblAlarmSound.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlarmSound.setForeground(Color.WHITE);
		lblAlarmSound.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblAlarmSound.setBounds(0, 100, 310, 37);
		contentPane.add(lblAlarmSound);
		
		JSpinner HourSpinner = new JSpinner();
		HourSpinner.setBounds(62, 346, 41, 36);
		HourSpinner.setFont(new Font("Tahoma", Font.PLAIN, 16));
		HourSpinner.setModel(new SpinnerNumberModel(23, 0, 23, 1));
		contentPane.add(HourSpinner);
		
		JSpinner MinuteSpinner = new JSpinner();
		MinuteSpinner.setModel(new SpinnerNumberModel(59, 0, 59, 1));
		MinuteSpinner.setFont(new Font("Tahoma", Font.PLAIN, 16));
		MinuteSpinner.setBounds(145, 346, 41, 36);
		contentPane.add(MinuteSpinner);
		
		JSpinner SecondSpinner = new JSpinner();
		SecondSpinner.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		SecondSpinner.setFont(new Font("Tahoma", Font.PLAIN, 16));
		SecondSpinner.setBounds(228, 346, 41, 36);
		contentPane.add(SecondSpinner);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(192, 192, 192));
		separator.setBackground(new Color(192, 192, 192));
		separator.setBounds(0, 75, 310, 7);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(192, 192, 192));
		separator_1.setBackground(new Color(192, 192, 192));
		separator_1.setBounds(0, 240, 310, 7);
		contentPane.add(separator_1);
		AlarmSoundPathTextArea.setEditable(false);
		
		AlarmSoundPathTextArea.setToolTipText("Sound file path for alarm");
		AlarmSoundPathTextArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
		AlarmSoundPathTextArea.setBounds(10, 148, 289, 40);
		AlarmSoundPathTextArea.setLineWrap(true);
		contentPane.add(AlarmSoundPathTextArea);
		
		JButton btnChangeSound = new JButton("Change Sound");
		btnChangeSound.setForeground(new Color(128, 128, 0));
		btnChangeSound.setBackground(new Color(255, 255, 255));
		btnChangeSound.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnChangeSound.setBounds(62, 199, 176, 30);
		contentPane.add(btnChangeSound);
		
		//ActionListener to change sound
		btnChangeSound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				try {					
					// Creates a JFileChooser Method to open file explorer and select a file
					File currentDir = new File(System.getProperty("user.dir"));
		            JFileChooser fileChooser = new JFileChooser();
		            fileChooser.setCurrentDirectory(currentDir);
		            
		            //Only show .wav audio files
		            fileChooser.setAcceptAllFileFilterUsed(false);
		            FileNameExtensionFilter wavFilter = new FileNameExtensionFilter("snd", "wav", "aiff");
		            fileChooser.addChoosableFileFilter(wavFilter);
		            
		            int r = fileChooser.showOpenDialog(null);
		            // if the user selects a file
		            if (r == JFileChooser.APPROVE_OPTION){
		            	alarm.setPathToSound(fileChooser.getSelectedFile().getAbsolutePath());
		            	AlarmSoundPathTextArea.setText(alarm.getPathToSound());
		            }
		            // if the user cancelled the operation
		            else
		            	JOptionPane.showMessageDialog(btnChangeSound,"File Selection Cancelled","Alert",JOptionPane.WARNING_MESSAGE);
					
				}catch (Exception ex) {

				}
	    }  
		});
		
		JLabel lblSetAlarm = new JLabel("Set Alarm");
		lblSetAlarm.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetAlarm.setForeground(Color.WHITE);
		lblSetAlarm.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblSetAlarm.setBounds(0, 250, 310, 37);
		contentPane.add(lblSetAlarm);
		
		txtAlarmName = new JTextField();
		txtAlarmName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtAlarmName.setToolTipText("Set name for alarm");
		txtAlarmName.setText("Alarm Name");
		txtAlarmName.setBounds(43, 298, 223, 37);
		contentPane.add(txtAlarmName);
		txtAlarmName.setColumns(10);
		
		JButton btnSetAlarm = new JButton("Set Alarm");
		btnSetAlarm.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSetAlarm.setBackground(new Color(255, 255, 255));
		btnSetAlarm.setForeground(new Color(128, 128, 0));
		btnSetAlarm.setBounds(80, 407, 149, 23);
		contentPane.add(btnSetAlarm);
		
		//ActionListener to set alarm
		btnSetAlarm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				try {
					alarm.setAlarmName(txtAlarmName.getText());
					alarm.setHour( (Integer) HourSpinner.getModel().getValue());
					alarm.setMinute( (Integer) MinuteSpinner.getModel().getValue());
					alarm.setSecond( (Integer) SecondSpinner.getModel().getValue());
					
					JOptionPane.showMessageDialog(btnSetAlarm,"Alarm Set Successfully");  
					
					System.out.println(alarm);
					
				}catch (Exception ex) {
				    JOptionPane.showMessageDialog(btnSetAlarm,"Error/Exception occurred while setting Alarm","Alert",JOptionPane.WARNING_MESSAGE);     

				}
	    }  
		});
		
		JLabel lblHour = new JLabel("HH");
		lblHour.setForeground(new Color(255, 255, 255));
		lblHour.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHour.setBounds(30, 346, 33, 37);
		contentPane.add(lblHour);
		
		JLabel lblMinute = new JLabel("MM");
		lblMinute.setForeground(Color.WHITE);
		lblMinute.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMinute.setBounds(113, 346, 33, 37);
		contentPane.add(lblMinute);
		
		JLabel lblSecond = new JLabel("SS");
		lblSecond.setForeground(Color.WHITE);
		lblSecond.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSecond.setBounds(196, 346, 33, 37);
		contentPane.add(lblSecond);
		
		new TimerThread().start();
	}
	
	class TimerThread extends Thread{
		public void run() {
			while(true) {
				alarm.updateCurrentTime();
				lblCurrentTime.setText(alarm.getCurrentTime());
				
				LocalTime tempTimeNow = LocalTime.now();
				tempTimeNow = tempTimeNow.withNano(0);
				LocalTime tempAlarmTime = LocalTime.of(alarm.getHour(), alarm.getMinute(), alarm.getSecond(), 0);
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
				
				if (tempTimeNow.format(dtf).compareTo(tempAlarmTime.format(dtf))==0) {
					try {
						File soundPath = new File(alarm.getPathToSound());
						
						AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundPath);
						Clip clipPlay = AudioSystem.getClip();
						clipPlay.open(audioIn);
						clipPlay.start();
						
						//To stop Clip thread from killing itself before audio plays
						JOptionPane.showMessageDialog(null,"ALARM: "+alarm.getAlarmName()+ "\nPress OK to Stop Alarm"); 						
						clipPlay.stop();
;						
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UnsupportedAudioFileException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (LineUnavailableException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				try {
					sleep(100);
				}catch(Exception ex) {
					//Catch all
				}
			}
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
