package com.pi.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;

import com.pi.classes.Pergunta;
import com.pi.classes.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcelo Júnior on 12/12/2017.
 */

public class PerguntaDao {
    public static final String TABELA = "pergunta";
    public static final String ID = "id";
    public static final String TEMPO_VERBAL = "tempo_verbal";
    public static final String PERGUNTA = "pergunta";
    public static final String RESP_1 = "resp1";
    public static final String RESP_2 = "resp2";
    public static final String RESP_3 = "resp3";
    public static final String RESP_4 = "resp4";
    public static final String CORRETA = "correta";

    public static boolean inserir(Context context) {
        List<String> perguntas = new ArrayList<>();
        // 5 underlines
        // os tempos verbais devem ser escritos "Present Perfect" "Past Simple" "Past Perfect"
        // sempre tem que colocar as 4 alternativas (mesmo não tendo), nenhuma resposta pode ser vazia. EX: ''

        perguntas.add("('Past Simple', '_____ your father use to _____ you to school? (to take)', 'does, took', 'did, taken', 'did, take', 'do, take', 3)");
        perguntas.add("('Past Simple', '_____ Anne win the match? (to win)', 'did', 'does', 'do', 'will', 1)");
        perguntas.add("('Past Simple', '_____ you _____ my towel? (to wet)', 'does, wet', 'did, weet', 'do, weet', 'did, wet', 4)");
        perguntas.add("('Past Simple', 'Did Jane _____ a beautiful dress? (to wear). \nNa sentença, o verbo correto é worn.', 'false', 'true', '', '', 1)");
        perguntas.add("('Past Simple', 'Did they _____ in a bad mood? (to wake)', 'woke', 'wake', 'awaken', 'woken', 2)");
        perguntas.add("('Past Simple', '_____ you _____ wine? (to drink)', 'will, drank', 'does, drink', 'do, drunk', 'did, drink', 4)");
        perguntas.add("('Past Simple', 'Did they _____ the game? (to lose). \nNa sentença, o verbo correto é lose.', 'false', 'true', '', '', 2)");
        perguntas.add("('Past Simple', '_____ Daniel tell you the news? (to tell). \nNa sentença, o verbo auxiliar é Did.', 'false', 'true', '', '', 2)");
        perguntas.add("('Past Simple', 'Did the teacher _____ you the subject? (to teach)', 'taugth', 'teacher', 'taught', 'teach', 4)");
        perguntas.add("('Past Simple', 'Did Kira _____ well today? (to swim)', 'swim', 'swum', 'swell', 'swam', 1)");
        perguntas.add("('Past Simple', '_____ he _____ the house? (to sweep)', 'did, wet', 'does, weet', 'did, sweep', 'do, wet', 3)");
        perguntas.add("('Past Simple', '_____ you _____ my pencil? (to steal)', 'did, stolen', 'did, steal', 'did, stole', 'do, steal', 2)");
        perguntas.add("('Past Simple', '_____ we know how to write that word? (to know). \nNa sentença, o verbo auxiliar é Do', 'false', 'true', '', '', 1)");
        perguntas.add("('Past Simple', 'Did you _____ a note? (to see). \nNa sentença, o verbo correto é saw.', 'false', 'true', '', '', 1)");
        perguntas.add("('Past Simple', 'Did you _____ her? (to meet)', 'meant', 'met', 'meeting', 'meet', 4)");
        perguntas.add("('Past Simple', 'Did you _____ like me? (to think)', 'taught', 'think', 'thought', 'thougth', 2)");
        perguntas.add("('Past Simple', '_____ you _____what I said?', 'did, understand', 'did, understood', 'do, understood', '', 1)");
        perguntas.add("('Past Simple', '_____ Alex _____ milk on you? (to spill)', 'do, spilt', 'did, spilt', 'did, spill', 'does, spelt', 3)");
        perguntas.add("('Past Simple', 'Did Alison _____ the bell? (to ring). Na sentença, o verbo correto é rang.', 'false', 'true', '', '', 1)");
        perguntas.add("('Past Simple', 'Did Josh _____ you the coat? (to lend). Na sentença, o verbo correto é lend.', 'false', 'true', '', '', 2)");
        perguntas.add("('Past Simple', 'I went to school yesterday. \nPasse para a forma negativa.', 'did not go', 'did not went', 'did not gone', 'will not go', 1)");
        perguntas.add("('Past Simple', 'He got a job. \nPasse para a forma negativa.', 'did not got', 'did not get', 'does not gave', '', 2)");
        perguntas.add("('Past Simple', 'She made lunch. \nPasse para a forma negativa.', 'do not', 'did not', 'does not', 'will not', 2)");
        perguntas.add("('Past Simple', 'It ran to the house. \nPasse para a forma negativa.', 'did not run', 'do not run', 'does not run','does run', 1)");
        perguntas.add("('Past Simple', 'I spoke with my friend yesterday. \nPasse para a forma negativa.', 'doesn’t speak', 'don’t spoken', 'didn’t spoke', 'didn’t speak', 4)");
        perguntas.add("('Past Simple', 'You understood your teacher very well. \nPasse para a forma negativa.', 'didn’t understand', 'don’t understand', 'didn’t understood', 'won’t understand', 1)");
        perguntas.add("('Past Simple', 'Andy bought a new shirt. \nNa sentença, o verbo auxiliar da forma negativa é do not.', 'true', 'false', '', '', 2)");
        perguntas.add("('Past Simple', 'She had a bath in the morning. \nNa sentença, o verbo auxiliar da forma negativa é did not.', 'true', 'false', '', '', 1)");
        perguntas.add("('Past Simple', 'We felt like a big ice cream. \nNa sentença, a forma negativa correta é We did not feel like a big ice cream.', 'false', 'true', '', '', 2)");
        perguntas.add("('Past Simple', 'The students sat down. \nNa sentença, a forma negativa correta é The students didn’t sat down.', 'false', 'true', '', '', 1)");
        perguntas.add("('Past Simple', 'The managers shook hands on the deal. \nPasse para a forma negativa.', 'did not shaken', 'do not shook', 'did not shook', 'did not shake', 4)");
        perguntas.add("('Past Simple', 'William rode a horse. \nPasse para a forma negativa.', 'do not ride', 'did not ride', 'did not rode', 'did not ridden', 2)");
        perguntas.add("('Past Simple', 'The nurse took Peter’s temperature. \nPasse para a forma negativa.', 'didn’t take', 'didn’t took', 'don’t take', 'don’t took', 1)");
        perguntas.add("('Past Simple', 'They swam on Saturday. \nPasse para a forma negativa.', 'doesn’t swam', 'didn’t swum', 'didn’t swim', 'don’t swim', 3)");
        perguntas.add("('Past Simple', 'She learnt French in high school. \nNa sentença, a forma negativa correta é She did not learnt French in high school.', 'true', 'false', '', '', 2)");
        perguntas.add("('Past Simple', 'Petra spoke to the teacher. \nNa sentença, a forma negativa incorreta é Petra did not speak to the teacher.', 'true', 'false', '', '', 2)");
        perguntas.add("('Past Simple', 'Jake built a model aeroplane. \nNa sentença, a forma negativa incorreta é Jake did not built a model aeroplane.', 'true', 'false', '', '', 1)");
        perguntas.add("('Past Simple', 'The police caught the thief. \nNa sentença, a forma negativa correta é The police did not catch the thief.', 'true', 'false', '', '', 1)");
        perguntas.add("('Past Simple', 'He hid the letter. \nPasse para a forma negativa.', 'did not hidden', 'did not hid', 'do not hide', 'did not hide', 4)");
        perguntas.add("('Past Simple', 'She thought about it. \nPasse para a forma negativa.', 'does not think', 'did not think', 'do not think', 'did not thought', 2)");
        perguntas.add("('Past Simple', 'I _____ to São Paulo last weekend. (to go)', 'go', 'went', 'gone', 'going', 2)");
        perguntas.add("('Past Simple', 'She _____ a few mistakes but, even so, she _____ the game. (to make, to win)', 'make, win', 'made, win', 'make, won', 'made, won', 4)");
        perguntas.add("('Past Simple', 'We _____ the information on the magazine’s website. (to find) \nO verbo correto da sentença é found.', 'false', 'true', '', '', 2)");
        perguntas.add("('Past Simple', 'She _____ a lot of money at the mall. (to spend) \nO verbo correto da sentença é spent.', 'false', 'true', '', '', 2)");
        perguntas.add("('Past Simple', 'They _____ sad at the defeat of the team. (to feel) \nO verbo correto da sentença é feel.', 'false', 'true', '', '', 1)");
        perguntas.add("('Past Simple', 'She _____ very well in the competition. (to swim)', 'swum', 'swam', 'swim', '', 2)");
        perguntas.add("('Past Simple', 'They _____ the same book. (to buy)', 'buy', 'buying', 'bought', '', 3)");
        perguntas.add("('Past Simple', 'They _____ the same book. (to read)', 'reading', 'read', '', '', 2)");
        perguntas.add("('Past Simple', 'She _____ to discuss. (to begin) \nO verbo correto da sentença é begin.', 'false', 'true', '', '', 1)");
        perguntas.add("('Past Simple', 'She _____ with her boyfriend. (to sleep) \nO verbo correto da sentença é sleep.', 'false', 'true', '', '', 1)");
        perguntas.add("('Past Simple', 'Lucia _____ her friend’s notebook and _____ her name. (to take, to write) \nOs verbos corretos da sentença são took e wrote.', 'false', 'true', '', '', 2)");
        perguntas.add("('Past Simple', 'Rodrigo _____ a dog but it died. (to have)', 'have', 'had', '', '', 2)");
        perguntas.add("('Past Simple', 'The butterfly _____ and fell on the little girl’s shoulder. (to fly)', 'flying', 'flown', 'flew', 'fly', 3)");
        perguntas.add("('Past Simple', 'We _____ all the food, there was nothing left. (to eat)', 'ate', 'eaten', 'eating', 'eat', 1)");
        perguntas.add("('Past Simple', 'Qual é o passado do verbo to see?', 'see', 'seft', 'saw', 'said', 3)");
        perguntas.add("('Past Simple', 'Qual o passado do verbo to speak?', 'speak', 'speaked', 'spell', 'spoke', 4)");
        perguntas.add("('Past Simple', 'Qual desse grupo de palavras possui palavras apenas no passado?', 'loved, dancing, read, freny', 'taller, dirty, spoke, ling', 'fly, three, true, spleep', 'saw, went, left, spoke', 4)");
        perguntas.add("('Past Simple', 'Qual o passado do verbo to steal?', 'stolen', 'stole', 'steal', '', 2)");
        perguntas.add("('Past Simple', 'O passado do verbo to get está correto em qual das alternativas?', 'got', 'gots', 'getting', 'get', 1)");
        perguntas.add("('Past Simple', 'He _____ who hurt him. (to forgive)', 'forgiven', 'forgave', 'forgot', 'forgive', 2)");


        perguntas.add("('Present Perfect', 'You _____ me just in time. (to find)', 'have find', 'have found', 'has find', 'had find', 2)");
        perguntas.add("('Present Perfect', 'They _____ to Australia many times. (to go)', 'have go', 'had went', 'have gone', 'has went', 3)");
        perguntas.add("('Present Perfect', 'I have already _____ the letter. (to write)', 'written', 'writing', 'wrote', 'write', 1)");
        perguntas.add("('Present Perfect', 'He  _____ some useful advice. (to give)', 'has give', 'have give', 'has given', 'have given', 3)");
        perguntas.add("('Present Perfect', 'She _____ with Mark lately. (to speak)', 'has speak', 'have spoke', 'have speaking', 'has spoken', 4)");
        perguntas.add("('Present Perfect', 'Messi _____ about the match. (to think)', 'has think', 'has thought', 'have think', 'have thought', 2)");
        perguntas.add("('Present Perfect', 'They _____ some time together. (to spend)', 'have spent', 'has spend', 'have spend', 'has spent', 1)");
        perguntas.add("('Present Perfect', 'The mother _____ her baby in her arms. (to hold)', 'has hold', 'has held', 'have nothing', 'have held', 1)");
        perguntas.add("('Present Perfect', 'Johnny _____ up from a nightmare. (to wake)', 'have waken', 'has woken', 'has waken', 'have woken', 2)");
        perguntas.add("('Present Perfect', 'Marta _____ Best FIFA Player award six times. (to win)', 'has win', 'has won', 'have win', 'have won', 2)");
        perguntas.add("('Present Perfect', 'Luka Modric _____ Cristiano Ronaldo to win the best male footballer award. (to beat)', 'has beat', 'had beating', 'has beaten', 'have beaten', 3)");
        perguntas.add("('Present Perfect', 'My parents _____ a new house. (to buy)', 'have buy', 'has buy', 'have bought', 'has bought', 3)");
        perguntas.add("('Present Perfect', 'I _____ French fries. (to eat)', 'have ate', 'has eat', 'have eaten', 'has eaten', 3)");
        perguntas.add("('Present Perfect', 'Caleb _____ a lot of homework lately. (to do)', 'has did', 'has done', 'have did', 'have done', 2)");
        perguntas.add("('Present Perfect', 'Steve Herly _____ a famous novelist. (to become)', 'has became', 'has become', 'have became', 'have become', 2)");
        perguntas.add("('Present Perfect', 'The journey _____. (to begin)', 'has begun', 'have began', 'have begun', 'has began', 1)");
        perguntas.add("('Present Perfect', 'A fantastic opportunity _____ for Jane. (to arise)', 'has arose', 'have arose', 'has arisen', 'have arisen', 3)");
        perguntas.add("('Present Perfect', 'Henry _____ his leg two times. (to break)', 'has broke', 'have broken', 'have broke', 'has broken', 2)");
        perguntas.add("('Present Perfect', 'I _____ my lunch. (to bring)', 'have bring', 'has bring', 'has brought', 'have brought', 4)");
        perguntas.add("('Present Perfect', 'Steve Jobs _____ a big business empire. (to build)', 'has built', 'have built', 'has build', 'have build', 1)");
        perguntas.add("('Present Perfect', 'They _____ my birthday. (to forget)', 'have not forgotten', 'has not forgotten', 'have not forget', 'has not forget', 1)");
        perguntas.add("('Present Perfect', 'Andressa _____ our secret. (to keep)', 'have not kept', 'have not keep', 'has not keep', 'has not kept', 4)");
        perguntas.add("('Present Perfect', 'He _____ for the team. (to choose)', 'has not chose', 'has not chosen', 'have not chose', 'have not chosen', 2)");
        perguntas.add("('Present Perfect', 'They _____ there. (to drive)', 'have not drove', 'have not driven', 'has not drove', 'has not driven', 4)");
        perguntas.add("('Present Perfect', 'My brother _____ lately. (to draw)', 'has not drawn', 'has not drew', 'have not drawn', 'has not drew', 1)");
        perguntas.add("('Present Perfect', 'Sasha _____ about bad things. (to dream)', 'has not dreamt', 'have not dreamt', 'has not dream', 'has not dream', 1)");
        perguntas.add("('Present Perfect', 'I _____ beer last month. (to drink)', 'has not drunk', 'have not drunk', 'has not drank', 'have not drank', 2)");
        perguntas.add("('Present Perfect', 'The bird _____ far away from here. (to fly)', 'have not flown', 'have not flew', 'has not flown', 'has not flew', 3)");
        perguntas.add("('Present Perfect', 'She _____ her friend. (to forgive)', 'have not forgiven', 'has not forgiven', 'have not forgave', 'has not forgave', 2)");
        perguntas.add("('Present Perfect', 'Alison _____ the truth. (to hide)', 'has not hid', 'have not hid', 'has not hidden', 'have not hidden', 3)");
        perguntas.add("('Present Perfect', 'She _____ me how to do it. (to teach)', 'have not taught', 'has not teach', 'has not taught', 'have not teach', 3)");
        perguntas.add("('Present Perfect', 'Eduarda _____ yesterday. (to sleep)', 'has not slept', 'has not sleep', 'have not slept', 'have not sleep', 1)");
        perguntas.add("('Present Perfect', 'Maeve _____ next to me. (to sit)', 'has not sit', 'have not sat', 'has not sat', 'have not sit', 2)");
        perguntas.add("('Present Perfect', 'He _____ from himself no more. (to run)', 'has not run', 'have not run', 'has not ran', 'have not ran', 3)");
        perguntas.add("('Present Perfect', 'My girlfriend _____ me yet. (to forgive)', 'have not forgiven', 'has not forgiven', 'have not forgave', 'has not forgave', 2)");
        perguntas.add("('Present Perfect', 'Luke and Matt _____ in months. (to fight)', 'has not fight', 'have not fight', 'has not fought', 'have not fought', 4)");
        perguntas.add("('Present Perfect', 'You _____ me home. (to drive)', 'have not drove', 'have not driven', 'has not drove', 'have not drive', 2)");
        perguntas.add("('Present Perfect', 'Luana _____ her hair lately. (to cut)', 'has not cut', 'have not cut', 'has not cutting', 'have not cutting', 1)");
        perguntas.add("('Present Perfect', 'They _____ to hang out recently. (to choose)', 'have not choose', 'have not chosen', 'has not choose', 'has not chosen', 2)");
        perguntas.add("('Present Perfect', 'You _____ pizza this week. (to eat)', 'has not eat', 'have not eat', 'has not eaten', 'have not eaten', 4)");
        perguntas.add("('Present Perfect', '_____ you ever _____ money? (to lose)', 'Have, lost', 'Have, lose', 'Has, lost', 'Has, lose', 1)");
        perguntas.add("('Present Perfect', '_____ you ever _____ a Halloween costume? (to wear)', 'Have, wear', 'Have, worn', 'Has, wear', 'Has, worn', 2)");
        perguntas.add("('Present Perfect', '_____ you ever _____ alcohol? (to drink)', 'Has, drink', 'Has, drunk', 'Have, drank', 'Have, drunk', 4)");
        perguntas.add("('Present Perfect', '_____ Jane _____ an award or a medal? (to win)', 'Has, win', 'Have, won', 'Has, won', 'Have, win', 3)");
        perguntas.add("('Present Perfect', '_____ Lucy _____ Valentine cards? (to get)', 'Have, got', 'Has, got', 'Have, get', 'Has, get', 2)");
        perguntas.add("('Present Perfect', '_____ Jake ever _____ his leg? (to break)', 'Have, break', 'Have, broken', 'Has, break', 'Has, broken', 4)");
        perguntas.add("('Present Perfect', 'What is the best book you _____ ever _____? (to read)', 'Has, read', 'Have, read', 'Has, reading', 'Have, reading', 2)");
        perguntas.add("('Present Perfect', '_____ they _____? (to swim)', 'Have, swim', 'Has, swam', 'Have, swum', 'Have, swam', 3)");
        perguntas.add("('Present Perfect', '_____ he _____ this pair of jeans? (to buy)', 'Has, buy', 'Have, bought', 'Have, buy', 'Has, bought', 4)");
        perguntas.add("('Present Perfect', '_____ you ever _____ the rain? (to see)', 'Have, seen', 'Have, see', 'Has, see', 'Has, seen', 1)");
        perguntas.add("('Present Perfect', '_____ she _____ her lunch? (to bring)', 'Has, brought', 'Have, bring', 'Have, brought', 'Has, bring', 1)");
        perguntas.add("('Present Perfect', '_____ he _____ the dog? (to feed)', 'Has, fed', 'Have, fed', 'Have, feed', 'Has, feed', 1)");
        perguntas.add("('Present Perfect', '_____ you _____ my secret? (to keep)', 'Has, keep', 'Have, kept', 'Have, keep', 'Has, kept', 2)");
        perguntas.add("('Present Perfect', '_____ Thaís _____ you? (to meet)', 'Has, meet', 'Have, met', 'Has, met', 'Have, meet', 3)");
        perguntas.add("('Present Perfect', '_____ she _____ about the news last night? (to hear)', 'Have, hear', 'Has, hear', 'Have, heard', 'Has, heard', 4)");
        perguntas.add("('Present Perfect', '_____ Amanda _____ you flowers? (to give)', 'Has, give', 'Have, gave', 'Have, given', 'Has, given', 4)");
        perguntas.add("('Present Perfect', '_____ I _____ you lately that I love you? (to tell)', 'Has, told', 'Have, told', 'Have, tell', 'Has, tell', 2)");


        perguntas.add("('Past Perfect', 'She _____ a good doctor. (to be)', 'had be', 'have been', 'had been', 'have be', 3)");
        perguntas.add("('Past Perfect', 'They _____ best friends. (to become)', 'had become', 'have become', 'has become', 'to had become', 1)");
        perguntas.add("('Past Perfect', 'I _____ my right arm. (to break)', 'have break','had break', 'has broken', 'had broken', 4)");
        perguntas.add("('Past Perfect', 'John _____ a goldfish. (to buy)', 'had buy', 'has buy', 'had bought', 'have bought', 3)");
        perguntas.add("('Past Perfect', 'Maria _____ a blue dress. (to choose)', 'had choosen', 'have chosen', 'has chouse', 'had chouse', 1)");
        perguntas.add("('Past Perfect', 'The cat _____ the milk. (to drink)', 'has drink', 'have drunk', 'had drunk', 'had drink', 3)");
        perguntas.add("('Past Perfect', 'She _____ off the tree. (to fall)', 'has fallen', 'has fall', 'have fall', 'had fallen', 4)");
        perguntas.add("('Past Perfect', 'He _____ to close the door. (to forget)', 'had forgotten', 'have forgot', 'has forget', 'had forget', 1)");
        perguntas.add("('Past Perfect', 'Emily _____ her history book. (to lend)', 'had lend', 'had lent', 'has lent', 'have lent', 2)");
        perguntas.add("('Past Perfect', 'She _____ the knees. (to hurt)', 'has hurt', 'had hurt', 'have a hurt', 'have not hurt', 2)");
        perguntas.add("('Past Perfect', 'He _____ a cookie. (to eat)', 'has eat', 'had eat', 'had eaten', 'have eaten', 3)");
        perguntas.add("('Past Perfect', 'She _____ her necklace. (to find)', 'had find', 'has found', 'had found', 'have find', 3)");
        perguntas.add("('Past Perfect', 'They _____ a house. (to leave)', 'had leave', 'has left', 'have left', 'had left', 4)");
        perguntas.add("('Past Perfect', 'Augusto _____ a cake. (to make)', 'had made', 'had make', 'has made', 'have make', 1)");
        perguntas.add("('Past Perfect', 'Ana _____ The Rebel of Sands. (to read)', 'had read', 'has read', 'have read', 'had not read', 1)");
        perguntas.add("('Past Perfect', 'We had ridden a bike. (to ride) \nO verbo correto é ridden:', 'true', 'false', '', '', 1)");
        perguntas.add("('Past Perfect', 'She had seen a bird. (to see) \nO verbo correto é seen:', 'true', 'false', '', '', 1)");
        perguntas.add("('Past Perfect', 'Your eyes had shone like a star. (to shine) \nO verbo correto é shone:', 'true', 'false', '', '', 1)");
        perguntas.add("('Past Perfect', 'She has sing in the bathroom. (to sing) \nO verbo correto é sing:', 'true', 'false', '', '', 2)");
        perguntas.add("('Past Perfect', 'He had swam with his friends. (to swim) \nO verbo correto é swam:', 'true', 'false', '', '', 2)");
        perguntas.add("('Past Perfect', 'He _____ the bad smell. (to bear)', 'had not borne', 'has not bear', 'have not borne', 'had borne', 1)");
        perguntas.add("('Past Perfect', 'She _____ the work. (to begin)', 'had not begin', 'had not begun', 'has not begun', 'have begin', 2)");
        perguntas.add("('Past Perfect', 'They _____ to the party. (to come)', 'has not come', 'have not come', 'had not come', 'not come', 3)");
        perguntas.add("('Past Perfect', 'He _____ the dinner. (to make)', 'have not make', 'has not made', 'had made', 'had not made', 4)");
        perguntas.add("('Past Perfect', 'They _____to London for so long. (to drive)', 'had not driven', 'has not drive', 'have drive', 'had drive', 1)");
        perguntas.add("('Past Perfect', 'Elena _____ well that morning. (to feel)', 'have not feel', 'had not felt', 'had not feel', 'has felt', 2)");
        perguntas.add("('Past Perfect', 'He _____ on Sunday. (to fight)', 'had not fight', 'has not fought', 'has not fougth', 'have fight', 3)");
        perguntas.add("('Past Perfect', 'We _____ the dog. (to feed)', 'has not fed', 'have not fed', 'had feed', 'had not fed', 4)");
        perguntas.add("('Past Perfect', 'The birds _____ away. (to fly)', 'had not flown','have flown', 'had not fly', 'has flown', 1)");
        perguntas.add("('Past Perfect', 'Emma _____ João.(to forgive)', 'has not forgiven','had not forgiven','have forgive','had not forgive', 2)");
        perguntas.add("('Past Perfect', 'I _____ the English book. (to get)', 'has not gotten', 'have gotten', 'had not gotten', 'had got', 3)");
        perguntas.add("('Past Perfect', 'He _____ given a hug to Mom. (to give)', 'had give', 'has not given', 'have not given', 'had not given', 4)");
        perguntas.add("('Past Perfect', 'I _____ to the party. (to go)', 'had not gone', 'have gone', 'has not go', 'had gone', 1)");
        perguntas.add("('Past Perfect', 'He _____ any children. (to beget)', 'have not begotten', 'had not begotten', 'has begotten', 'had not beget', 2)");
        perguntas.add("('Past Perfect', 'She _____ her finger. (to cut)', 'had cut', 'has not cut', 'had not cut', 'have cut', 3)");
        perguntas.add("('Past Perfect', 'She had not heard Sunset Lover. (to hear) \nO verbo correto é heard', 'true', 'false', '','', 1)");
        perguntas.add("('Past Perfect', 'He had not hit the goal. (to hit) \nO verbo correto é hit:', 'true', 'false', '', '', 1)");
        perguntas.add("('Past Perfect', 'I had not hurt my fist. (to hurt) \nO verbo correto é hurt:', 'true','false', '', '', 1)");
        perguntas.add("('Past Perfect', 'She had not keep an umbrela. (to keep) \nO verbo correto é keep:', 'true', 'false', '', '', 2)");
        perguntas.add("('Past Perfect', 'He had not lay without couch. (to lay) \nO verbo correto é lay:', 'true', 'false', '', '', 2)");
        perguntas.add("('Past Perfect', '_____ she _____ the monster? (to slay)', 'Had, slain', 'Had, slew', 'Had, slay', 'Have, slew', 1)");
        perguntas.add("('Past Perfect', '_____ she _____ home? (to leave)', 'Had she left', 'Had she leave', 'Had she leave', 'Have she left', 2)");
        perguntas.add("('Past Perfect', '_____ Maju _____ her cell phone? (to lose)', 'Had, lose', 'Have, lost', 'Had, lost', 'Has, lose', 3)");
        perguntas.add("('Past Perfect', '_____ Clay _____: I love you? (to mean)', 'Have, mean', 'Had, meant', 'Had, mean', 'Had, meant', 4)");
        perguntas.add("('Past Perfect', '_____ he _____ another woman? (to meet)', 'Had, met', 'Has, met', 'Have, met', 'Had, meant', 1)");
        perguntas.add("('Past Perfect', '_____ she _____ the marathon? (to overcome)', 'Have, overcame', 'Had, overcome', 'Had, overcame', 'Have, overcome', 2)");
        perguntas.add("('Past Perfect', '_____ you _____ the light bill? (to pay)', 'Had, paid', 'Have, paid', 'Had, pay', 'Have, pay', 1)");
        perguntas.add("('Past Perfect', '_____ Lucia _____ the bell? (to ring)', 'Had, ring', 'Had, rung', 'Had, rang', 'Had, run', 2)");
        perguntas.add("('Past Perfect', '_____ responsabilities _____ on the new job? (to rise)', 'Had, risen', 'Had, rose', 'Have, risen', 'Has, rose', 1)");
        perguntas.add("('Past Perfect', '_____ Sophie _____ the secret? (to tell)', 'Has, tell', 'Had, told', 'Have, told', 'Had, tell', 2)");
        perguntas.add("('Past Perfect', '_____ she _____ in the park? (to run)', 'Had, ran', 'Have, run', 'Had, run', 'Has, ran', 3)");
        perguntas.add("('Past Perfect', '_____ he said he _____ pizza? (to say)', 'Had, say', 'Has, said', 'Have, said', 'Had, said', 4)");
        perguntas.add("('Past Perfect', '_____ she _____ medical help? (to seek)', 'Had, sought', 'Had, seek', 'Have, sought', 'Have, seek', 1)");
        perguntas.add("('Past Perfect', '_____ Madalena _____ a letter? (to send)', 'Have, sent', 'Had, sent', 'Has, sent', 'Had, send', 2)");
        perguntas.add("('Past Perfect', '_____ Joe _____ you his scores in the game? (to show)', 'Had, show', 'Have, shown', 'Had, shown', 'Has, shown', 3)");
        perguntas.add("('Past Perfect', 'She had not done the nails. (to do) \nO verbo correto é done:', 'true', 'false', '', '', 1)");
        perguntas.add("('Past Perfect', 'Marcela had fallen off the bike. (to fall) \nO verbo correto é fallen:', 'true', 'false', '', '', 1)");
        perguntas.add("('Past Perfect', 'The Aelin had not left the room. (to leave) \nO verbo correto é left:', 'true', 'false', '', '', 1)");
        perguntas.add("('Past Perfect', 'Dorian had shine like a star. (to shine) \nO verbo correto é shine:', 'true', 'false', '', '', 2)");
        perguntas.add("('Past Perfect', 'She had not thoght about the song. (to think) \nO verbo correto é think:', 'true', 'false', '', '', 2)");


        //180 perguntas (60 Past Simple, 60 Present Perfect e 60 Past Perfect)

        String sql = "insert into " + TABELA + " (" + TEMPO_VERBAL + ", " + PERGUNTA + ", " + RESP_1 + ", " + RESP_2 + ", " + RESP_3 + ", " + RESP_4 + ", " + CORRETA + ") VALUES ";
        Banco bd = new Banco(context);
        for (String pergunta : perguntas) {
            bd.executar(sql + pergunta);
        }

        bd.close();

        return false;
    }

//    private static boolean atualizar(Context context, Usuario usuario) {
//        Object[] valor = null;
//        String[] campo = null;
//
//
//        if (usuario.getIdServidor() == 0) {
//            valor = new Object[]{usuario.getNickname(), usuario.getEmail(), usuario.getSenha()};
//            campo = new String[]{PERGUNTA, TEMPO_VERBAL, RESP_2};
//        } else {
//            valor = new Object[]{usuario.getIdServidor(), usuario.getNickname(), usuario.getEmail(), usuario.getSenha()};
//            campo = new String[]{CORRETA, PERGUNTA, TEMPO_VERBAL, RESP_2};
//        }
//
//        Banco bd = new Banco(context);
//        long linhasAfetadas = bd.atualizar(TABELA, campo, valor, usuario.getId());
//        bd.close();
//
//        if (linhasAfetadas > 0) {
//            return true;
//        }
//
//        return false;
//    }

//    public static boolean salvar(Context context, Usuario usuario) {
//        if (usuario.getId() == 0)
//            return inserir(context, usuario);
//        else
//            return atualizar(context, usuario);
//    }

    /*
        public static boolean excluir(Context context, Produto produto) {
            if(produto.getStatus() == EStatus.OK_EXCLUIR) {
                Banco bd = new Banco(context);
                int n = bd.excluir(TABELA, produto.getId());
                bd.close();
                if (n == 1) {
                    return true;
                }
                return false;
            }
            else{
                return salvar(context, produto);
            }
        }

        public static List<Produto> buscar(Context context){
            //removerTabela(context);
            return buscar(context, null, null, null, null, null);
        }

        public static List<Produto> buscar(Context context, String where, String[] argumento){
            //removerTabela(context);
            return buscar(context, where, argumento, null, null, null);
        }
*/
    public static Pergunta buscar(Context context, String where, String[] argumento, String groupBy, String having, String orderBy) {
        Banco bd = new Banco(context);
        Pergunta pergunta = new Pergunta();

        try {
            Cursor cursor = bd.buscar(TABELA, new String[]{ID, TEMPO_VERBAL, PERGUNTA, RESP_1, RESP_2, RESP_3, RESP_4, CORRETA}, where, argumento, groupBy, having, orderBy);

            while (!cursor.isAfterLast()) {
                pergunta.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                pergunta.setTempoVerbal(cursor.getString(cursor.getColumnIndex(TEMPO_VERBAL)));
                pergunta.setPergunta(cursor.getString(cursor.getColumnIndex(PERGUNTA)));
                pergunta.setResp1(cursor.getString(cursor.getColumnIndex(RESP_1)));
                pergunta.setResp2(cursor.getString(cursor.getColumnIndex(RESP_2)));
                pergunta.setResp3(cursor.getString(cursor.getColumnIndex(RESP_3)));
                pergunta.setResp4(cursor.getString(cursor.getColumnIndex(RESP_4)));
                pergunta.setCorreta(cursor.getInt(cursor.getColumnIndex(CORRETA)));

                cursor.moveToNext();
            }
            cursor.close();
        } catch (SQLiteException e) {
            if (e.getMessage().startsWith("no such table")) {
                criarTabela(context);
                pergunta = buscar(context, where, argumento, groupBy, having, orderBy);
            } else {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pergunta;
    }

    public static void criarTabela(Context context) {
        Banco bd = new Banco(context);
        bd.criarTabela(TABELA, new String[]{
                        ID,
                        TEMPO_VERBAL,
                        PERGUNTA,
                        RESP_1,
                        RESP_2,
                        RESP_3,
                        RESP_4,
                        CORRETA},
                new String[]{
                        Banco.INTEGER_PRIMARY_KEY_AUTOINCREMENT,
                        Banco.TEXT_NOT_NULL,
                        Banco.TEXT_NOT_NULL_UNIQUE,
                        Banco.TEXT,
                        Banco.TEXT,
                        Banco.TEXT,
                        Banco.TEXT,
                        Banco.INTEGER});
        bd.close();
    }

//    private static void removerTabela(Context context) {
//        Banco bd = new Banco(context);
//        bd.removeTabela(TABELA);
//    }

}
