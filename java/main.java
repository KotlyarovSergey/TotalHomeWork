class Main{
    public static void main(String[] args) {
        String repoFileName = "test.dat";
        Repository repo = new Repository(repoFileName);
        View view = new View();
        Controller controller = new Controller(view, repo);
        controller.run();
    }
}