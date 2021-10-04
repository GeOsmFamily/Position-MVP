<?php

namespace App\Notifications;

use Illuminate\Bus\Queueable;
use Illuminate\Notifications\Notification;
use Illuminate\Contracts\Queue\ShouldQueue;
use Illuminate\Notifications\Messages\MailMessage;
use Illuminate\Support\Facades\URL;

class SendEmailParams extends Notification
{
    use Queueable;

    protected $phone;
    protected $password;

    public function __construct($phone, $password)
    {
        $this->phone = $phone;
        $this->password = $password;
    }


    public function via($notifiable)
    {
        return ['mail'];
    }


    public function toMail($notifiable)
    {
        $phone = $this->phone;
        $password = $this->password;

        $url = $this->make_url($notifiable);

        return (new MailMessage)
            ->subject('Connection Settings')
            ->view('emails.account_settings', compact('phone', 'password', 'url'));
    }


    public function toArray($notifiable)
    {
        return [
            //
        ];
    }

    private function make_url($notifiable)
    {
        return URL::temporarySignedRoute(
            'register.verification',
            now()->addMinutes(config('auth.verification.expire')),
            ['user' => $notifiable->email]
        );
    }
}
