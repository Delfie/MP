package panel.menu;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import main.MainFrame;
import panel.BasePanel;
import panel.PanelManager;
import panel.menu.PanelLevelChoice;
import panel.home.PanelSignup;
import thread.GameThread;
import user.UserManager;

public class PanelMenu extends BasePanel {
	private PanelManager panel;
	
	// 정적변수
	private static final String GAME_START = "게임시작";
	private static final String WORD_SETTING = "단어입력";
	private static final String RANKING = "랭킹보기";
	private static final String LOGOUT = "로그아웃";
	// 버튼 이미지
	private ImageIcon gameIcon = new ImageIcon("images/게임하기.png");
	private ImageIcon wordIcon = new ImageIcon("images/단어입력.png");
	private ImageIcon rankingIcon = new ImageIcon("images/랭킹보기.png");
	private ImageIcon logoutIcon = new ImageIcon("images/로그아웃.png");
	private ImageIcon gameIcon_ = new ImageIcon("images/게임하기2.png");
	private ImageIcon wordIcon_ = new ImageIcon("images/단어입력2.png");
	private ImageIcon rankingIcon_ = new ImageIcon("images/랭킹보기2.png");
	private ImageIcon logoutIcon_ = new ImageIcon("images/로그아웃2.png");
	// 버튼
	private JButton btnGameStart = new JButton(GAME_START, gameIcon);
	private JButton btnWordSetting = new JButton(WORD_SETTING, wordIcon);
	private JButton btnRanking = new JButton(RANKING, rankingIcon);
	private JButton btnLogout = new JButton(LOGOUT, logoutIcon);
	// 리스너
	private MenuActionEvent menuAction = new MenuActionEvent();
	private MenuMouseEvent menuEvent = new MenuMouseEvent();

	@Override
	public void initPanel() {

	}

	public PanelMenu(PanelManager panel) {
		super("images/menuBG.png");
		setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
		setLocation(0, 25);
		this.panel = panel;

		setMenu(); // 버튼 세팅
		setButtonListener(); // 리스너 세팅
	}

	/** 패널 내의 메뉴 버튼 리스너 세팅 */
	private void setButtonListener() {
		btnGameStart.addActionListener(menuAction);
		btnWordSetting.addActionListener(menuAction);
		btnRanking.addActionListener(menuAction);
		btnLogout.addActionListener(menuAction);

		btnGameStart.addMouseListener(menuEvent);
		btnWordSetting.addMouseListener(menuEvent);
		btnRanking.addMouseListener(menuEvent);
		btnLogout.addMouseListener(menuEvent);
	}

	/** 패널 내의 메뉴 버튼들 설정 및 부착 */
	private void setMenu() {
		btnGameStart.setBorderPainted(false);
		btnGameStart.setFocusPainted(false);
		btnGameStart.setContentAreaFilled(false);
		btnGameStart.setBounds(445, 100, 370, 100);

		btnWordSetting.setBorderPainted(false);
		btnWordSetting.setFocusPainted(false);
		btnWordSetting.setContentAreaFilled(false);
		btnWordSetting.setBounds(445, 220, 370, 100);

		btnRanking.setBorderPainted(false);
		btnRanking.setFocusPainted(false);
		btnRanking.setContentAreaFilled(false);
		btnRanking.setBounds(445, 340, 370, 100);

		btnLogout.setBorderPainted(false);
		btnLogout.setFocusPainted(false);
		btnLogout.setContentAreaFilled(false);
		btnLogout.setBounds(445, 460, 370, 100);

		add(btnGameStart); // 붙여! 쵹
		add(btnWordSetting); // 쵹
		add(btnRanking); // 쵹
		add(btnLogout); // 쵹
	}

	public BasePanel getBasePanel() {
		return this; // 왜만들었더라ㅎㅎ
	}

	/** 메뉴패널에 대한 버튼들의 액션리스너 */
	class MenuActionEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton pressedBtn = (JButton) e.getSource();
			switch (pressedBtn.getText()) {
			case GAME_START:
				panel.setContentPane(PanelManager.LEVELCHOICE);
				panel.getLevelChoicePanel().setButtonEnable(); // 버튼 비활성화 설정
				break;
			case WORD_SETTING:
				panel.setContentPane(PanelManager.WORD_SETTING);
				panel.getWordSettingPanel().setFocus(); // 포커스
				break;
			case RANKING:
				panel.setContentPane(PanelManager.RANKING);
				break;
			case LOGOUT:
				panel.setContentPane(PanelManager.HOME);
				break;
			default:
				break;
			}
		}
	}

//	/** 게임 구동 스레드를 생성하고 게임을 실행 */
//	public void gameStart() {
//		GameThread newGame = new GameThread(panel.getGamePanel());
//		int stage = 1; /* 스테이지를 결정할 수 있는 패널 만들기! */
//		newGame.setGame(UserManager.user, stage);
//		newGame.start();
//		newGame.setFocus();
//	}

	/** 메뉴패널에 대한 버튼들의 마우스리스너 */
	class MenuMouseEvent extends MouseAdapter {
		// 아무것도 안할 때,
		public void mouseExited(MouseEvent e) {
			JButton eventBtn = (JButton) e.getSource();
			switch (eventBtn.getText()) {
			case GAME_START:
				btnGameStart.setIcon(gameIcon);
				break;
			case WORD_SETTING:
				btnWordSetting.setIcon(wordIcon);
				break;
			case RANKING:
				btnRanking.setIcon(rankingIcon);
				break;
			case LOGOUT:
				btnLogout.setIcon(logoutIcon);
				break;
			}
		}

		// 버튼 위에 마우스를 올릴 때,
		public void mouseEntered(MouseEvent e) {
			JButton eventBtn = (JButton) e.getSource();
			switch (eventBtn.getText()) {
			case GAME_START:
				btnGameStart.setIcon(gameIcon_);
				break;
			case WORD_SETTING:
				btnWordSetting.setIcon(wordIcon_);
				break;
			case RANKING:
				btnRanking.setIcon(rankingIcon_);
				break;
			case LOGOUT:
				btnLogout.setIcon(logoutIcon_);
				break;
			}
		}
	}
}
