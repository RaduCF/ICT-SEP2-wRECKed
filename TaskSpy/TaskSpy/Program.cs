using System;
using System.Text;

using System.Runtime.InteropServices;
using System.Threading;

using System.Net;
using System.Net.Sockets;

using System.Diagnostics;
using System.Runtime.InteropServices;
using System.IO;
using System.Windows.Forms;

namespace TaskSpy
{
    class Program
    {

        [DllImport("user32.dll")]
        static extern IntPtr GetForegroundWindow();

        [DllImport("user32.dll")]
        static extern Int32 GetWindowThreadProcessId(IntPtr hWnd, out uint lpdwProcessId);

        static string GetForegroundProcessName()
        {
            IntPtr hwnd = GetForegroundWindow();

            if (hwnd == null)
                return "Unknown";


            uint pid;
            GetWindowThreadProcessId(hwnd, out pid);

            foreach (System.Diagnostics.Process p in System.Diagnostics.Process.GetProcesses())
            {
                if (p.Id == pid)
                    return p.ProcessName;
            }

            return "Unknown";
        }

        static ASCIIEncoding encoder = new ASCIIEncoding();

        static Socket clientSocket;

        static bool GetLocalIPAddress(ref string ip_)
        {
            var host = Dns.GetHostEntry(Dns.GetHostName());
            foreach (var ip in host.AddressList)
            {
                if (ip.AddressFamily == AddressFamily.InterNetwork)
                {
                    ip_ = ip.ToString();
                    return true;
                }
            }
            return false;
        }

        static bool sendString(string str)
        {
            try
            {
                clientSocket.Send(BitConverter.GetBytes(Encoding.ASCII.GetByteCount(str)));
                clientSocket.Send(Encoding.ASCII.GetBytes(str));
            }
            catch (Exception)
            {
                return false;
            }

            return true;
        }

        static void Main(string[] args)
        {
            string ip = "";
            if (!GetLocalIPAddress(ref ip))
                return;

            try
            {
                IPEndPoint serverAddress = new IPEndPoint(IPAddress.Parse(ip), 5000);
                clientSocket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
                clientSocket.Connect(serverAddress);
            }
            catch (Exception)
            {
                return;
            }

            string oldTitle = " ";
            string title = "";

            int x, y;
            x = y = 0;

            int time = 0;

            while (true)
            {
                Thread.Sleep(1000);

                title = GetForegroundProcessName();

                //Check if user is idle, by reading mouse movement
                if (x == Cursor.Position.X && y == Cursor.Position.Y)
                {
                    time++;
                }
                else
                {
                    time = 0;
                    x = Cursor.Position.X;
                    y = Cursor.Position.Y;
                }

                if (time >= 30)
                {
                    title = "AFK";
                }

                if (oldTitle == title)
                    continue;

                oldTitle = title;

                //Only send data when Focus has changed
                if (!sendString(title))
                    return;

            }

        }
    }
}
