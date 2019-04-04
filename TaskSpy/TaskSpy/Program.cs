using System;
using System.Text;

using System.Runtime.InteropServices;
using System.Threading;

using System.Net;
using System.Net.Sockets;

namespace TaskSpy
{
    class Program
    {
        [DllImport("user32.dll")]
        static extern IntPtr GetForegroundWindow();
        [DllImport("user32.dll")]
        static extern int GetWindowText(IntPtr hWnd, StringBuilder text, int count);

        static string GetActiveWindowTitle()
        {
            const int nChars = 256;
            StringBuilder Buff = new StringBuilder(nChars);
            IntPtr handle = GetForegroundWindow();

            if (GetWindowText(handle, Buff, nChars) > 0)
            {
                return Buff.ToString();
            }
            return null;
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
                int toSendLen = System.Text.Encoding.ASCII.GetByteCount(str);
                byte[] toSendBytes = System.Text.Encoding.ASCII.GetBytes(str);
                byte[] toSendLenBytes = System.BitConverter.GetBytes(toSendLen);
                clientSocket.Send(toSendLenBytes);
                clientSocket.Send(toSendBytes);
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
            while (true)
            {
                Thread.Sleep(5000);

                title = GetActiveWindowTitle();

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
