/*
 * Copyright (C) 2012 Rui Gonçalves and Daniel Cibrão
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.ruippeixotog.pexplorer.actions;

import net.ruippeixotog.pexplorer.R;
import net.ruippeixotog.pexplorer.app.PermissionAction;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;

public class WriteSettingsAction extends PermissionAction {

	public WriteSettingsAction() {
		super(R.string.write_settings_label, R.string.write_settings_desc,
				PermissionAction.WARN);
	}

	@Override
	protected void doAction(final Context context) {

		boolean isEnabled = Settings.System.getInt(
				context.getContentResolver(), Settings.System.AIRPLANE_MODE_ON,
				0) == 1;
		Settings.System.putInt(context.getContentResolver(),
				Settings.System.AIRPLANE_MODE_ON, isEnabled ? 0 : 1);
		Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
		intent.putExtra("state", !isEnabled);
		context.sendBroadcast(intent);

		new AlertDialog.Builder(context)
				.setTitle(R.string.write_settings_label)
				.setMessage(R.string.write_settings_changed_msg)
				.setCancelable(true)
				.setPositiveButton(R.string.continue_,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
							}
						}).show();
	}
}
