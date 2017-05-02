class Comments(object):
    def __init__(self, text):
        self.text = text
        self.actual_sate = 0
        self.pos = 0

        self.NOMRAL = 0
        self.COM_SLASH = 1
        self.COM_STAR = 2

    def pars_string(self):

        while (self.pos < len(self.text)):
            if self.actual_sate == self.NOMRAL:
                self.proces_normal_state()
            elif self.actual_sate == self.COM_SLASH:
                self.proces_com_slash()
            elif self.actual_sate == self.COM_STAR:
                self.proces_com_star()

    def proces_normal_state(self):
        if self.text[self.pos] == '/' and len(self.text) > self.pos + 1:

            if self.text[self.pos + 1] == '/':
                self.actual_sate = self.COM_SLASH
                print('Comentariu cu // \n')
            elif self.text[self.pos + 1] == '*':
                self.actual_sate = self.COM_STAR
                print('Comentariu cu /* \n')

            self.pos += 2

        elif self.text[self.pos] != ' ' and self.text[self.pos] != '\n' and self.text[self.pos] != '\r':
            print('EROARE : ', self.text[self.pos])
            self.pos += 1

        else:
            self.pos += 1

    def proces_com_slash(self):
        if self.text[self.pos] == '\n':
            self.actual_sate = self.NOMRAL

        self.pos += 1

    def proces_com_star(self):
        if self.text[self.pos] == '*' and len(self.text) > self.pos + 1 and self.text[self.pos + 1] == '/':
            self.actual_sate = self.NOMRAL
            self.pos += 2
        else:
            self.pos += 1


if __name__ == '__main__':
    with open("test_comments.txt") as f:
        comments = Comments(f.read())
        comments.pars_string()
