	� G 6/accs/073syuriken_b_red/mdl/syuriken_b_effect_yoko.kmd��       �T�n�P�!%R��J7
~$mYXY�MT
}Hm(�{�\�/�{S�VY�eWX�`^B]����q��_0���	ذ@�e{f�̜3�s��6����pA���x!�{��0J�R�za���D��bA���es��?��OV��<����ZfOj��[t(�XF��PjH9�\9*���
�P��C���.-c��؀g�6�o�̝՛��6:���X1����������{P[�v�H9ԧ��g�,�b���g�.�|.C¹�U�ħ�z��ו�e�z��iZU�T� �����*G��׾�=;��������/_�F�w����qt�!:y��E���y4�M^Fǧ��u4y��
Y7��>0"@^Xomc��!9Ĳ��]��р�>�f�d`��O�f�E�W6��28X���\I()��0c$	�>Et�s�#�GS�r��.��XF��,<$���P]|����.T���n�U8�����W�k:�P����1�F�VYqݸPl����������4E[D�8��Nu*�����3�EZ�Փu�8����h$ñ�F����)��,�H�p��!��?�Q��M<�czp<c5����E��rꙋ�ǝ��-Nk�vV]�~r-�܅X�Q��1�w67�o��c��&��\i6gI�?��Е�y��e#�0�4��x�0tq��O��=`������d��`0x�M5e�1�h�v���/��u�h��>�゜49X���? �,!�Y   E  Lga2/stage/AmpedScript; e Lkotori/data/MixtureData; mix�� kotori.data.MixtureData� /efx/mix/bar1.agi���� Lkotori/gfx/KGLScriptModel; beambeam start hit hitstage 	finishKGL (Lkotori/gfx/KGLScriptModel;)V
 f o p
 H 0 3 anime (F)V
 d s t roll (Lkotori/geom/Vector3D;I)V
 f v w-    ��       under12	 d z  generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d | }D 1/accs/073syuriken_b_red/syuriken_all_beambeam.scm�            ?�  ?�  ?�               effect_yoko kill
 f � 1 	playSound
 d � 3 setColor (FFFF)V
 d � � rotate (Lkotori/geom/Quaternion;I)V
 f � ��� kotori.geom.Quaternion?�               finishAllKGL
 f � 1D 1/accs/073syuriken_b_red/syuriken_all_beambeam.scm�            ?�  ?�  ?�               vibe (FFFFI)V
 d � ��#�
CH   attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �-            � '� � hit_c1  d�   �     c d 	fadeColor
 d � �-            � � �  d�   �     c d setBlend
 d � 3 setRenderType
 d � 3-            � d� � hit_be  d�   �     c d /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d | �D /efx/explode/exp_sphere1.scm�            ?   ?   ?                
setVisible (Z)V
 f � � f h g k j - 1  i� �  l 1  :*�� qW*� rW*� uW*y� xW*� {� *�� ~�*ж rW*� �W�  m 1  *� rW*� �W�  n 1  �*�� qW�   *� �W*�� �W*� �W*� rW*� {� *��� ~�*� �W*�� ȶ �W*��� �W* ȶ rW*X� �W* ȶ rW*��� �W*�� rW*� �W*� �W*� �W*d� rW*��� �W*�� �W*� �W*d� rW*� �W*�� rW*� �W� ��>            >��o=c�0>��o� � changemodel� ;/accs/073syuriken_b_red/mdl/syuriken_b_effect_yoko_beam.kmd