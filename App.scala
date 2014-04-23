import javax.media.opengl._
import com.jogamp.newt.event.WindowAdapter
import com.jogamp.newt.event.WindowEvent
import com.jogamp.newt.opengl.GLWindow
import com.jogamp.opengl.util.FPSAnimator

object Sketch {
	def main (args: Array[String]) {
		val glp = GLProfile.getDefault
		val caps = new GLCapabilities(glp)

		val window = GLWindow.create(caps)
		window.setSize(300, 300)
		window.setVisible(true)
		window.setTitle("TEST")

		window.addWindowListener( new WindowAdapter() {
			override def windowDestroyNotify (e : WindowEvent) {
				exit(0)
			}
		})

		window.addGLEventListener( new GLEventListener () {
			var theta : Double = 0
			var s : Double = 0
			var c : Double = 0

			override def display (drawable : GLAutoDrawable) {
				println("display")
				update
				render(drawable)
			}

			override def dispose (drawable : GLAutoDrawable) {
				println("dispose")
			}

			override def init (drawable : GLAutoDrawable) {
				println("init")
			}

			override def reshape (drawable : GLAutoDrawable, x : Int, y : Int, w : Int, h : Int) {
				println("reshape")
			}

			def update () {
				theta = theta + 0.01
				s = Math.sin(theta)
				c = Math.sin(theta)
			}

			def render (drawable : GLAutoDrawable) {
				println("render")
				val gl = drawable.getGL().getGL2();

				gl.glClear(GL.GL_COLOR_BUFFER_BIT);

				gl.glBegin(GL.GL_TRIANGLES);
				gl.glColor3f(1, 0, 0);
				gl.glVertex2d(-c, -c);
				gl.glColor3f(0, 1, 0);
				gl.glVertex2d(0, c);
				gl.glColor3f(0, 0, 1);
				gl.glVertex2d(s, -s);
				gl.glEnd();
			}
		})

		val animator = new FPSAnimator(window, 60);
		animator.start();

	}

}
