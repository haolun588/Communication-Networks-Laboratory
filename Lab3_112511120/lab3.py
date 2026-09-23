# -*- coding: utf-8 -*-
import tkinter as tk
from tkinter import messagebox

# Global variables
board = [[0, 0, 0], [0, 0, 0], [0, 0, 0]]
current_player = 1
default_bg_color = None

# Create board
def create_board():
    global default_bg_color
    for i in range(3):
        window.grid_rowconfigure(i, weight=1)
        window.grid_columnconfigure(i, weight=1)
        for j in range(3):
            button = tk.Button(
                window,
                text="",
                font=("Arial", 50),
                height=2,
                width=6,
                command=lambda row=i, col=j: handle_click(row, col)
            )
            button.grid(row=i, column=j, sticky="nsew")
            if default_bg_color is None:
                default_bg_color = button.cget("bg")

# Handle button clicks
def handle_click(row, col):
    global current_player, board

    # Check which button has been clicked and change player
    if board[row][col] == 0:
        button = window.grid_slaves(row=row, column=col)[0]
        if current_player == 1:
            board[row][col] = 'X'
            button.config(text='X')
            current_player = 2
        else:
            board[row][col] = 'O'
            button.config(text='O')
            current_player = 1

        check_winner()

# Check for a winner or a tie
def check_winner():
    winner = None
    winner_path = []

    # Check rows
    for i in range(3):
        if board[i][0] != 0 and board[i][0] == board[i][1] == board[i][2]:
            winner = board[i][0]
            winner_path = [(i, 0), (i, 1), (i, 2)]
            break

    # Check columns
    if not winner:
        for j in range(3):
            if board[0][j] != 0 and board[0][j] == board[1][j] == board[2][j]:
                winner = board[0][j]
                winner_path = [(0, j), (1, j), (2, j)]
                break

    # Check diagonals
    if not winner:
        if board[0][0] != 0 and board[0][0] == board[1][1] == board[2][2]:
            winner = board[0][0]
            winner_path = [(0, 0), (1, 1), (2, 2)]
        elif board[0][2] != 0 and board[0][2] == board[1][1] == board[2][0]:
            winner = board[0][2]
            winner_path = [(0, 2), (1, 1), (2, 0)]

    # Check if tie
    if not winner:
        tie = True
        for r in range(3):
            for c in range(3):
                if board[r][c] == 0:
                    tie = False
                    break
        if tie:
            winner = "tie"

    if winner:
        declare_winner(winner, winner_path)

# Declare the winner and ask to restart the game
def declare_winner(winner, winner_path):
    if winner == "tie":
        # 遊戲平手，將全部 button 的背景改為紅色
        for i in range(3):
            for j in range(3):
                button = window.grid_slaves(row=i, column=j)[0]
                button.config(bg="red")
        answer = messagebox.askyesno("Game Over", "It's a tie! Do you want to restart the game?")
    else:
        # 獲勝時，改變連線 button 背景顏色 (例如青藍色 cyan)
        for (r, c) in winner_path:
            button = window.grid_slaves(row=r, column=c)[0]
            button.config(bg="cyan")
        answer = messagebox.askyesno("Game Over", f"Player {winner} wins! Do you want to restart the game?")

    # 詢問玩家是否繼續遊戲
    if answer:
        # 重新開始一局
        global board, current_player
        board = [[0, 0, 0], [0, 0, 0], [0, 0, 0]]
        current_player = 1
        for i in range(3):
            for j in range(3):
                button = window.grid_slaves(row=i, column=j)[0]
                button.config(text="", bg=default_bg_color)
    else:
        # 關閉視窗
        window.destroy()

if __name__ == '__main__':
    # create main window
    window = tk.Tk()
    window.title("Lab3 OOXX")

    # create game board
    create_board()

    # Initialize variables
    board = [[0, 0, 0], [0, 0, 0], [0, 0, 0]]
    current_player = 1

    window.mainloop()
