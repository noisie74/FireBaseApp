package michael.com.firebaseapp.data;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import rx.Observable;

/**
 * Created by Mikhail on 2/9/17.
 */

public interface IUserAuth {

    Observable<FirebaseUser> observeAuthState(FirebaseAuth firebaseAuth);
}
