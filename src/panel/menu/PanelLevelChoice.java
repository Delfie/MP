package panel.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.attribute.UserPrincipalLookupService;

import javax.swing.JButton;
import javax.swing.JLabel;

import main.MainFrame;
import panel.BasePanel;
import panel.PanelManager;
import thread.GameThread;
import user.User;
import user.UserManager;

public class PanelLevelChoice extends BasePanel {
	private PanelManager panel;
	// 레이블
	JLabel stringBoxLevelChoice;
	// 버튼
	JButton[] level;
	// 위치 정적변수
	private static final int X = 70;
	private static final int Y = 100;

	public PanelLevelChoice(int x, int y, int width, int height, PanelManager panel) {
		super(/*이미지 경로*/);
		this.panel = panel;
		setBounds(x, y, width, height);
		setBackground(Color.CYAN); // 삭제 예정 라인

		setComponent(); // 버튼 세팅
		setListener();
	}

	/** 리스너 설정 */
	private void setListener() {
		for (int i = 0; i < level.length; i++) {
			level[i].addActionListener(new LevelChoiceListener());
		}
	}

	/** 컴포넌트 설정 및 배치 */
	@SuppressWarnings("deprecation")
	private void setComponent() {
		stringBoxLevelChoice = new JLabel("Level 선택", JLabel.CENTER);
		stringBoxLevelChoice.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		stringBoxLevelChoice.setBounds(100, 10, 200, 100);

		level = new JButton[10];
		for (int i = 0; i < level.length; i++) {
			level[i] = new JButton("Level." + Integer.toString(i + 1));
			level[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
			if (i < 5) {
				level[i].setBounds(X, Y + (50 * i), 115, 40);
			}
			else {
				level[i].setBounds(X + 150, Y + (50 * (i - 5)), 115, 40);
			}
			add(level[i]);
		}
		add(stringBoxLevelChoice);
	}

	public void setButtonEnable() {
		for (int i = UserManager.user.getLastStage(); i < level.length; i++) {
			level[i].setEnabled(false);
		}
	}

	/** 패널 내 버튼에 대한 리스너 */
	class LevelChoiceListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton pressedBtn = (JButton) e.getSource();

			switch (pressedBtn.getText()) {
			case "back":
				// 뒤로가기 처리
				break;
			default:
				String tmp = pressedBtn.getText();
				int selectedLevel = Integer.parseInt(tmp.substring(tmp.length() - 1));
				panel.setContentPane(PanelManager.GAME);
				gameStart(selectedLevel);
				// 게임에서 다시 메인메뉴로 돌아올때를 위해 setVisivle(false)를 해야하나?
				break;
			}

		}
	}

	/** 게임 구동 스레드를 생성하고 게임을 실행 */
	public void gameStart(int level) {
		repaint(); // 넣어야 될지 다시 생각해보기
		GameThread newGame = new GameThread(panel.getGamePanel());
		newGame.setGame(UserManager.user, level);
		newGame.start();
		newGame.setFocus();
	}

	@Override
	public void initPanel() {
		// TODO Auto-generated method stub
	}

}
