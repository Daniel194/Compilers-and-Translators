using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Parsare
{
    class Program
    {
        public enum TextState
        {
            normalState = 0,
            comWithDoubleSlash = normalState + 1,
            comWithStar = comWithDoubleSlash + 1
        };
        static private string m_string;
        static private int m_actualState;
        static private int m_index = 0;
        [STAThread]
        public static void Main()
        {

            var dialog = new OpenFileDialog
            {
                Multiselect = false,
                Title = "Open text file",
                Filter = "Text file|*.txt;*.txt"
            };
            using (dialog)
            {
                if (dialog.ShowDialog() == System.Windows.Forms.DialogResult.OK)
                {
                    //m_string = dialog.FileName;
                    m_string = System.IO.File.ReadAllText(dialog.FileName);
                    // code executed on opened excel file goes here.
                }
                else
                    return;
            }

            //m_string = System.IO.File.ReadAllText(@"E:\Log.txt");
            m_actualState = (int)TextState.normalState;
            ParseString();
            Console.ReadLine();
        }
        static private void ParseString()
        {
            while (m_index < m_string.Length)
                switch (m_actualState)
                {
                    case (int)TextState.normalState:
                        ProcesNormalState();
                        break;
                    case (int)TextState.comWithDoubleSlash:
                        ProcesDoubleSlashState();
                        break;
                    case (int)TextState.comWithStar:
                        ProcesStarState();
                        break;

                }

        }
        static private void ProcesNormalState()
        {
            if ('/' == m_string[m_index] && m_string.Length > m_index + 1 && '/' == m_string[m_index + 1])
            {
                m_actualState = (int)TextState.comWithDoubleSlash;
                Console.WriteLine("");
                Console.Write("Comentariu cu / :");
                Console.Write(m_string[m_index]);
                Console.Write(m_string[m_index + 1]);
                m_index += 2;
                return;
            }
            if ('/' == m_string[m_index] && m_string.Length > m_index + 1 && '*' == m_string[m_index + 1])
            {
                m_actualState = (int)TextState.comWithStar;
                Console.WriteLine("");
                Console.Write("Comentariu cu * :");
                Console.Write(m_string[m_index]);
                Console.Write(m_string[m_index + 1]);
                m_index += 2;
                return;
            }
            if (m_string[m_index] != ' ' && m_string[m_index] != '\n' && m_string[m_index] != '\r')
                Console.WriteLine("Eroare :" + m_string[m_index]);
            m_index++;
            return;
        }
        static private void ProcesDoubleSlashState()
        {
            if ('\n' == m_string[m_index])// && m_string.Length > m_index + 1 && 'n' == m_string[m_index + 1])
            {
                m_actualState = (int)TextState.normalState;
                //Console.WriteLine("** Normal State **");
                Console.Write(m_string[m_index]);
                //Console.Write(m_string[m_index + 1]);
                //Console.WriteLine("");
                m_index++;
                return;
            }
            Console.Write(m_string[m_index]);
            m_index++;
            return;
        }
        static private void ProcesStarState()
        {
            if ('*' == m_string[m_index] && m_string.Length > m_index + 1 && '/' == m_string[m_index + 1])
            {
                m_actualState = (int)TextState.normalState;
                //Console.WriteLine("** Normal State **");
                Console.Write(m_string[m_index]);
                Console.Write(m_string[m_index + 1]);
                Console.WriteLine("");
                m_index += 2;
                return;
            }
            Console.Write(m_string[m_index]);
            m_index++;
            return;
        }

    }
}




