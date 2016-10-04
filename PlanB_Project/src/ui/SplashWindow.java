package ui;

import com.sun.awt.AWTUtilities;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JWindow;
import java.awt.Frame;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public final class SplashWindow extends JWindow{

	private static final long serialVersionUID = 1L;

	public SplashWindow(String filename, Frame f, int waitTime)
	{
		super(f);
                JLabel l = new JLabel(new ImageIcon(filename));
                AWTUtilities.setWindowOpaque(this,false);
		getContentPane().add(l, BorderLayout.CENTER);
                setProgressBar(new JProgressBar());
                pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension labelSize = l.getPreferredSize();
		setLocation(screenSize.width/2 - (labelSize.width/2),screenSize.height/2 - (labelSize.height/2));

		final int pause = waitTime;
		final Runnable closerRunner = new Runnable()
		{
			public void run()
			{
				setVisible(false);
				dispose();
			}
		};
		
		Runnable waitRunner = new Runnable()
		{
			public void run()
			{
				try
				{
					Thread.sleep(pause);
					SwingUtilities.invokeAndWait(closerRunner);
				}
				catch(Exception e)
				{
					e.printStackTrace();
		
				}
			}
		};
		setVisible(true);
		Thread splashThread = new Thread(waitRunner, "SplashThread");
		splashThread.start();
	}
        
        public void setProgressBar(JProgressBar b){
            this.getContentPane().add(b, BorderLayout.SOUTH);
        }
}
