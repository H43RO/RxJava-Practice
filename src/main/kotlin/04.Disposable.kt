import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

fun main(){

    // Disposable
    // - 여러 이유로 작업을 취소해야할 수 있음
    // - 이 때 Disposable 을 dispose() 함으로써 작업을 취소할 수 있음
    // - 여러 Disposable 들을 일일히 dispose() 하면 귀찮으므로 CompositeDisposable 사용
    // - 기본적으로 add 로 Disposable 을 등록하고, clear 로 등록된 작업을 모두 dispose() 함

    val compositeDisposable= CompositeDisposable()
    compositeDisposable.addAll(
        Observable.just(1).subscribe(),
        Single.just(1).subscribe(),
        Maybe.just(1).subscribe()
    )

    compositeDisposable.clear() // addAll() 할 때 등록했던 모든 Disposable(Observable) 의 dispose() 가 이루어 짐
}