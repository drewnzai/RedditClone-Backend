import { Injectable } from "@angular/core";
import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { AuthService } from "./services/auth.service";
import { BehaviorSubject, catchError, Observable, switchMap, throwError, filter, take } from "rxjs";
import { LoginResponse } from "./models/login-response.payload";

@Injectable({
    providedIn: 'root'
})
export class TokenInterceptor implements HttpInterceptor{
    isTokenRefreshing = false;
    refreshTokenSubject: BehaviorSubject<any> = new BehaviorSubject(null);
    
    constructor(public authService: AuthService){

    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        
        if (req.url.indexOf('refresh') !== -1 || req.url.indexOf('login') !== -1) {
            return next.handle(req);
        }
        const jwtToken = this.authService.getJwtToken();

        return next.handle(this.addToken(req, jwtToken)).pipe(catchError(error => {
            if (error instanceof HttpErrorResponse
                && error.status === 403) {
                return this.handleAuthErrors(req, next);
            } else {
                return throwError(error);
            }
        }));
    }

    private handleAuthErrors(req: HttpRequest<any>, next: HttpHandler)
        : Observable<HttpEvent<any>> {
        if (!this.isTokenRefreshing) {
            this.isTokenRefreshing = true;
            this.refreshTokenSubject.next(null);

            return this.authService.refreshToken().pipe(
                switchMap((refreshTokenResponse: unknown) => {
                    const response = refreshTokenResponse as LoginResponse;
                    this.isTokenRefreshing = false;
                    this.refreshTokenSubject
                        .next(response.authenticationToken);
                    return next.handle(this.addToken(req,
                        response.authenticationToken));
                })
            )
        } else {
            return this.refreshTokenSubject.pipe(
                filter(result => result !== null),
                take(1),
                switchMap((res) => {
                    return next.handle(this.addToken(req,
                        this.authService.getJwtToken()))
                })
            );
        }
    }

    addToken(req: HttpRequest<any>, jwtToken: any) {
        return req.clone({
            headers: req.headers.set('Authorization',
                'Bearer ' + jwtToken)
        });
    }

}
