/**
 * Author: Jatin Mittal
 * Date: 26-09-2024
 */

package com.Feedy.response;

import com.Feedy.Enum.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String jwt;
    private String message;
    private UserRole userRole;


}
