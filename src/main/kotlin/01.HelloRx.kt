import io.reactivex.Observable

fun main(){
    Observable // 생산자 : 데이터를 생산하여 전달
        .just("Hello!", "RxJava!?") // 생성 연산자
        .map { it.dropLast(1) } // 연산자 : 데이터를 가공 (맨 뒤에 문자 하나 제거)
        .subscribe(::println) // 소비자 : 데이터를 받아서 처리 (여기선 println)
}