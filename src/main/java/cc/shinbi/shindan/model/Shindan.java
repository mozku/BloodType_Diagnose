package cc.shinbi.shindan.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Shindan {
	private List<Result> results;
	private List<Question> questions;

	Shindan() {
		this.results = new ArrayList<Result>();
		this.questions = new ArrayList<Question>();
	}

	public List<Result> getResults() {
		return results;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void shuffle() {
		for (Question question : this.questions) {
			question.shuffle();
		}

		Collections.shuffle(this.questions);
	}

	private static List<Result> getResultList() {
		Result[] results = { 
				new Result("A型", "あなたの性格はA型に近いようです"),
				new Result("B型", "あなたの性格はB型に近いようです"),
				new Result("O型", "あなたの性格はO型に近いようです"),
				new Result("AB型", "あなたの性格はAB型に近いようです"), 
				};

		List<Result> list = Arrays.asList(results);
		return list;
	}

	private static List<Question> getQuestionList() {
		List<Question> list = new ArrayList<Question>();

		Question question1 = new Question("q1", "旅行の持ち物はどの程度持っていきますか？");
		question1.addItem(0, "あらゆる状況に想定して。");
		question1.addItem(1, "マイペースに直前に準備する");
		question1.addItem(2, "余計なものは持って行かない現地調達");
		question1.addItem(3, "ついつい余計なものまで持っていく");
		list.add(question1);

		Question question2 = new Question("q2", "旅先ではどのように過ごしますか");
		question2.addItem(0, "朝から夜までガッツリ観光する");
		question2.addItem(1, "ライブやイベントを全力で楽しむ");
		question2.addItem(2, "ホテルや旅館でまったりのんびり過ごす");
		question2.addItem(3, "ご当地グルメを食べつくす");
		list.add(question2);

		Question question3 = new Question("q3", "買い物に行った時についてあなたに1番あてはまるものは");
		question3.addItem(0, "衝動買いはあまりしない");
		question3.addItem(1, "気になるものがあったら迷わず購入する");
		question3.addItem(2, "なんとなくでついいらないモノまで買ってします");
		question3.addItem(3, "優柔不断で買い物するのに時間がかかる");
		list.add(question3);

		Question question4 = new Question("q4", "よく聞く音楽のプレイリストは");
		question4.addItem(0, "好きなアーティストのマイベストのプレイリスト");
		question4.addItem(1, "流行の曲を次々と入れたプレイリスト");
		question4.addItem(2, "通勤用、勉強用などの用途別のプレイリスト");
		question4.addItem(3, "懐メロなど同世代が聞かないような曲");
		list.add(question4);

		Question question5 = new Question("q5", "日頃どんな本を読みますか");
		question5.addItem(0, "ジャンル関係なくなんでも読む");
		question5.addItem(1, "好きなジャンルの中でもマニアックな本を読む");
		question5.addItem(2, "感情移入できるような小説");
		question5.addItem(3, "映画化やアニメ化された原作漫画");
		list.add(question5);

		return list;
	}

	public static Shindan createShindan() {

		Shindan shindan = new Shindan();
		List<Result> results = getResultList();
		shindan.getResults().addAll(results);

		List<Question> questions = getQuestionList();
		shindan.getQuestions().addAll(questions);

		shindan.shuffle();

		return shindan;

	}

	public Result check(List<Integer> answers) {
		int[] counters = new int[this.results.size()];
		Arrays.fill(counters, 0);

		for (Integer index : answers) {
			counters[index]++;
		}

		int index = 0;
		int maxCount = 0;
		for (int i = 0; i < counters.length; i++) {
			int counter = counters[i];
			if (counter > maxCount) {
				index = i;
				maxCount = counter;
			}
		}

		return this.results.get(index);

	}
}
